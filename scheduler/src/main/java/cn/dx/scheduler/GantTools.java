package cn.dx.scheduler;

import cn.hutool.core.io.file.FileWriter;

public class GantTools {
    private int [] seq;
    private double [] lastLength;
    private FileWriter writer;

    public GantTools(int[] seq, double[] lastLength) {
        this.seq = seq;
        this.lastLength = lastLength;
    }

    public void build(String filePath) {
        writer = new FileWriter(filePath,"utf-8");
        writer.append("@startgantt\n");
        for (int i = 0; i < seq.length; i++) {
            int idx = seq[i];
            String format = String.format("[序号 %d] lasts %f days\n", idx, lastLength[idx]);
            System.out.print(format);
            writer.append(format);
        }

        for (int i = 0; i < seq.length -1 ; i++) {
            String format = String.format("[序号 %d] -> [序号 %d]\n", seq[i], seq[i + 1]);
            System.out.print(format);
            writer.append(format);
        }
    }

    public void end(){
        writer.append("@endgantt\n");
    }
}
