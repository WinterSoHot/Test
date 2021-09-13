package cn.dx.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。
 * <p>
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x2f9gg/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author dongxian
 * @version 0.1
 * @date 2020/12/3
 **/
public class ValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        Set<Character> set = new HashSet<>(9);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char ch = board[i][j];
                if (ch == '.') {
                    continue;
                }
                if (set.contains(ch)) {
                    return false;
                }
                set.add(ch);
            }
            set.clear();

            for (int j = 0; j < 9; j++) {
                char ch = board[j][i];
                if (ch == '.') {
                    continue;
                }
                if (set.contains(ch)) {
                    return false;
                }
                set.add(ch);
            }
            set.clear();
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int x = i * 3, y = j * 3;
                for (int k = 0; k < 3; k++) {
                    for (int z = 0; z < 3; z++) {
                        char ch = board[x + k][y + z];
                        if (ch == '.') {
                            continue;
                        }
                        if (set.contains(ch)) {
                            return false;
                        }
                        set.add(ch);
                    }
                }
                set.clear();
            }
        }

        return true;
    }
}
