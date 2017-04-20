package org.jason.automan;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by Jason.Xia on 16/10/9.
 */
public class ProcesserFactory {
    private ConcurrentMap<String/*projectName*/, Processer> processerConcurrentMap = new ConcurrentHashMap<>();

    public Processer createProcesser(ProcesserContext processerContext, ProgressListener listener) {
        if (null == processerContext || null == processerContext.getProjectName() || 0 == processerContext
                .getProjectName().length()) {
            throw new IllegalArgumentException("ProcesserContext not be null.");
        }

        if (processerConcurrentMap.containsKey(processerContext.getProjectName())) {
            return processerConcurrentMap.get(processerContext.getProjectName());
        }

        processerConcurrentMap.putIfAbsent(processerContext.getProjectName(), new Processer(processerContext,
                listener));
        return processerConcurrentMap.get(processerContext.getProjectName());
    }
}
