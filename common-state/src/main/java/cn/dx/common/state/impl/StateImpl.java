package cn.dx.common.state.impl;

import cn.dx.common.state.EventCode;
import cn.dx.common.state.State;
import cn.dx.common.state.Status;
import cn.dx.common.state.exception.StateNotCanTransitException;
import cn.dx.common.state.exception.StateRepeatTransitException;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author gudongxian
 * @date 2021/7/22
 */
public class StateImpl implements State {
    private Table<String, String, String> statusTable = HashBasedTable.create();
    private Set<String> startStatusSet = new HashSet<>();
    private Set<String> endStatusSet = new HashSet<>();

    @Override
    public void assertTransit(Status source, EventCode eventCode, Status target) {
        String targetCode;
        if (source == null) {
            // 起始点，还没有源状态
            targetCode = statusTable.get(Status.EDEN_STATUS, eventCode.getCode());
        } else {
            targetCode = statusTable.get(source.getCode(), eventCode.getCode());
        }
        if (!target.getCode().equals(targetCode)) {
            if (Objects.equals(source, target)) {
                throw new StateRepeatTransitException("status = " + source + ",event = " + eventCode);
            } else {
                throw new StateNotCanTransitException("source=" + source + ",event=" + eventCode + ",expectTarget=" + target + ",actualTarget=" + targetCode);
            }
        }
    }

    @Override
    public boolean isStartStatus(Status status) {
        return startStatusSet.contains(status.getCode());
    }

    @Override
    public boolean isEndStatus(Status status) {
        return endStatusSet.contains(status.getCode());
    }

    public Table<String, String, String> getStatusTable() {
        return statusTable;
    }

    public void setStatusTable(Table<String, String, String> statusTable) {
        this.statusTable = statusTable;
    }

    public Set<String> getStartStatusSet() {
        return startStatusSet;
    }

    public void setStartStatusSet(Set<String> startStatusSet) {
        this.startStatusSet = startStatusSet;
    }

    public Set<String> getEndStatusSet() {
        return endStatusSet;
    }

    public void setEndStatusSet(Set<String> endStatusSet) {
        this.endStatusSet = endStatusSet;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("\n@startuml\n");
        statusTable.cellSet().forEach(cell ->
                builder.append(cell.getRowKey()).append(" --> [")
                        .append(cell.getColumnKey()).append("]")
                        .append(cell.getValue()).append("\n")
        );
        builder.append("@enduml");
        return builder.toString();
    }
}
