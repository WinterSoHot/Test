package cn.dx.common.state;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author gudongxian
 * @date 2021/7/22
 */
@ConfigurationProperties("spring.common.state")
public class StateProperties {
    /**
     * uml文件路径
     */
    private List<String> filepaths;
    /**
     * 状态机类包路径
     */
    private List<String> packagenames;

    public List<String> getFilepaths() {
        return filepaths;
    }

    public void setFilepaths(List<String> filepaths) {
        this.filepaths = filepaths;
    }

    public List<String> getPackagenames() {
        return packagenames;
    }

    public void setPackagenames(List<String> packagenames) {
        this.packagenames = packagenames;
    }
}
