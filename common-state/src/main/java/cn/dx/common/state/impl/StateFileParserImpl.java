package cn.dx.common.state.impl;

import cn.dx.common.state.State;
import cn.dx.common.state.StateFileParser;
import cn.dx.common.state.Status;
import cn.dx.common.state.exception.StateFileParseException;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author gudongxian
 * @date 2021/7/22
 */
public class StateFileParserImpl implements StateFileParser {
    private static final int TWO = 2;

    @Override
    public State parserFile(Class<? extends Status> statusClass, Resource resource) {
        FileParseContext fileParseContext = FileParseContext.create(statusClass);
        try (InputStream inputStream = resource.getInputStream()) {
            List<String> lines = IOUtils.readLines(inputStream, "UTF-8");
            int count = 0;
            for (String line : lines) {
                count++;
                line = line.trim();
                line = line.replaceAll(" +", " ");
                if ("@startuml".equals(line)) {
                    continue;
                }
                if ("@enduml".equals(line)) {
                    continue;
                }
                if (line.isEmpty()) {
                    continue;
                }
                parseLine(resource, fileParseContext, count, line);
            }
        } catch (IOException e) {
            throw new StateFileParseException("state file parse error: fileName=" + resource.getFilename(), e);
        }
        return fileParseContext.getState();
    }

    private void parseLine(Resource resource, FileParseContext fileParseContext, int count, String line) {
        String[] strings = line.split(" ");
        assertTrue(strings.length == 3, " length != 3", resource.getFilename(), line, count);
        assertTrue("-->".equals(strings[1]), "-->", resource.getFilename(), line, count);
        String source = strings[0];
        if (Status.EDEN_STATUS.equals(strings[TWO])) {
            fileParseContext.getState().getEndStatusSet().add(source);
        } else {
            String[] arr = strings[TWO].split("]");
            assertTrue(arr.length == 2, " length != 2", resource.getFilename(), line, count);
            String eventCode = arr[0].substring(1);
            String target = arr[1];
            assertTrue(fileParseContext.getState().getStatusTable().get(source, eventCode) == null,
                    "repeat line", resource.getFilename(), line, count);
            fileParseContext.getState().getStatusTable().put(source, eventCode, target);
            assertTrue(fileParseContext.getStatusCodes().contains(target) && fileParseContext.getEventCodes().contains(eventCode),
                    "status code or event code value error",
                    resource.getFilename(),
                    line,
                    count);
            if (Status.EDEN_STATUS.equals(source)) {
                fileParseContext.getState().getStartStatusSet().add(target);
            }
        }
    }

    private void assertTrue(boolean expression, String message, String fileName, String line, int row) {
        if (!expression) {
            throw new StateFileParseException(
                    "state file parse error, format error," + message + ":fileName="
                            + fileName + ",content="
                            + line + ",row=" + row);
        }
    }

}
