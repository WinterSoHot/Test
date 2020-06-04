package cn.dx.io;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Element;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author dongxian
 * @version 1.0
 * @className MyEditor
 * @description TODO
 * @date 20-5-15 下午2:20
 **/
public class MyEditor extends JTextPane {

    protected StyledDocument doc;
    protected SyntaxFormatter formatter = new SyntaxFormatter("my.stx");
    private SimpleAttributeSet normalAttr = formatter.getNormalAttributeSet();
    private SimpleAttributeSet quotAttr = new SimpleAttributeSet();

    //保存文档改变的开始位置
    private int docChangeStart = 0;
    // 保存文档改变的长度
    private int docChangeLength = 0;

    private MyEditor() {
        StyleConstants.setForeground(quotAttr, new Color(255, 0, 255));
        StyleConstants.setFontSize(quotAttr, 16);
        this.doc = super.getStyledDocument();
        this.setMargin(new Insets(3, 40, 0, 0));
        // 添加按键监听器，松开则进行语法分析
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent keyEvent) {
                syntaxParse();
            }
        });
        // 添加文档监视器
        doc.addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                docChangeStart = documentEvent.getOffset();
                docChangeLength = documentEvent.getLength();
//                System.out.printf("检测到文档变化，插入位置%d插入长度：%d%n", docChangeStart, docChangeLength);
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {

            }

            @Override
            public void changedUpdate(DocumentEvent documentEvent) {

            }
        });

    }

    private void syntaxParse() {
        try {
            // 获取文档的根元素，即文档内的全部内容
            Element root = doc.getDefaultRootElement();
            // 获取文档中光标插入符的位置
            int cursorPos = this.getCaretPosition();
            int line = root.getElementIndex(cursorPos);
            // 获取光标所在行
            Element para = root.getElement(line);
            // 定义光标所在行的行头在文档中的位置
            int start = para.getStartOffset();
            start = Math.min(start, docChangeStart);
            // 定义被修改部分的长度
            int length = para.getEndOffset() - start;
            length = length < docChangeLength ? docChangeLength + 1 : length;
            // 取出所有可能修改的字符串
            String s = doc.getText(start, length);
            System.out.println("当前可能被修改的字符串：" + s);
            // 以空格、点号作为分割符
            String[] tokens = s.split("\\s+|\\.|\\(|\\)|\\{|\\}|\\[|\\]");
            // 定义当前分析单词在s字符串中开始的位置
            int curStart = 0;
            // 定义单词是否处于引号内
            boolean isQuot = false;
            for (String token : tokens) {
                // 找出当前单词在s字符中的位置
                int tokenPos = s.indexOf(token, curStart);
                if (isQuot && (token.endsWith("\"") || token.endsWith("\'"))) {
                    doc.setCharacterAttributes(start + tokenPos,
                            token.length(), quotAttr, false);
                    isQuot = false;
                } else if (isQuot && !(token.endsWith("\"") || token.endsWith("\'"))) {
                    doc.setCharacterAttributes(start + tokenPos, token.length(),
                            quotAttr, false);
                } else if ((token.startsWith("\"") || token.startsWith("\'")) &&
                        (token.endsWith("\"") || token.endsWith("\'"))) {
                    doc.setCharacterAttributes(start + tokenPos, token.length(),
                            quotAttr, false);
                } else if ((token.startsWith("\"") || token.startsWith("\'"))
                        && !(token.endsWith("\"") || token.endsWith("\'"))) {
                    doc.setCharacterAttributes(start + tokenPos, token.length(),
                            quotAttr, false);
                    isQuot = true;
                } else {
                    formatter.setHighLight(doc, token, start + tokenPos, token.length());
                }
                // 开始分析下一个单词
                curStart = tokenPos + token.length();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // 重画组件，设置行号
    public void paint(Graphics g) {
        super.paint(g);
        Element root = doc.getDefaultRootElement();
        int line = root.getElementIndex(doc.getLength());
        g.setColor(new Color(230, 230, 230));
        //绘制现实行数的矩形框
        g.fillRect(0, 0, this.getMargin().left - 10, getSize().height);
        // 设置行号的颜色
        g.setColor(new Color(40, 40, 40));
        // 每行绘制一个行号
        for (int count = 0, j = 1; count <= line; count++, j++) {
            g.drawString(String.valueOf(j), 3, (int) ((count + 1) * 1.535
                    * StyleConstants.getFontSize(normalAttr)));
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("TextEditor");

        frame.getContentPane().add(new JScrollPane(new MyEditor()));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final int inset = 50;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds(inset, inset, screenSize.width - inset * 2,
                screenSize.height - inset * 2);
        frame.setVisible(true);
    }

    // 定义语法格式器
    class SyntaxFormatter {
        // 使用Map保存关键字和颜色的关系
        private Map<SimpleAttributeSet, ArrayList<String>> attMap = new HashMap<>();

        // 定义文档正常文本的外观属性
        SimpleAttributeSet normalAttr = new SimpleAttributeSet();

        public SyntaxFormatter(String syntaxFile) {
            StyleConstants.setForeground(normalAttr, Color.BLACK);
            StyleConstants.setFontSize(normalAttr, 16);

            // 创建Scanner对象，根据语法文件加载颜色信息
            Scanner scanner = null;
            try {
                scanner = new Scanner(new File(syntaxFile));
            } catch (FileNotFoundException e) {
                throw new RuntimeException("丢失语法文件：" + e.getMessage());
            }
            int color = -1;
            ArrayList<String> keywords = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.startsWith("#")) {
                    if (keywords.size() > 0 && color > -1) {
                        SimpleAttributeSet att = new SimpleAttributeSet();
                        StyleConstants.setForeground(att, new Color(color));
                        StyleConstants.setFontSize(att, 16);
                        attMap.put(att, keywords);
                    }
                    keywords = new ArrayList<>();
                    color = Integer.parseInt(line.substring(1), 16);
                } else {
                    // 普通行，每行内容加入关键字List
                    if (line.trim().length() > 0) {
                        keywords.add(line.trim());
                    }
                }
            }
            //把所有关键字和颜色对应起来
            if (keywords.size() > 0 && color > -1) {
                SimpleAttributeSet att = new SimpleAttributeSet();
                StyleConstants.setForeground(att, new Color(color));
                StyleConstants.setFontSize(att, 16);
                attMap.put(att, keywords);
            }
        }

        //返回该格式器正常文本的外观属性
        public SimpleAttributeSet getNormalAttributeSet() {
            return normalAttr;
        }

        //设置语法高亮
        public void setHighLight(StyledDocument doc, String token, int start, int length) {
            // 保存当前颜色对应的关键字
            SimpleAttributeSet currentAttributeSet = null;
            outer:
            for (SimpleAttributeSet att : attMap.keySet()) {
                ArrayList<String> keywords = attMap.get(att);
                for (String keyword : keywords) {
                    if (keyword.equals(token)) {
                        currentAttributeSet = att;
                        break outer;
                    }
                }
            }
            // 如果外观属性不为空
            if (currentAttributeSet != null) {
                doc.setCharacterAttributes(start, length,
                        currentAttributeSet, false);
            } else {
                doc.setCharacterAttributes(start, length, normalAttr, false);
            }
        }
    }
}
