package graphql.servlet.instrumentation;

import graphql.execution.ExecutionId;
import graphql.execution.instrumentation.InstrumentationState;
import org.dataloader.DataLoaderRegistry;

import java.util.List;

/**
 * Dispatching approach that expects to know about all executions before execution starts.
 */
public class RequestLevelTrackingApproach extends AbstractTrackingApproach {

    public RequestLevelTrackingApproach(List<ExecutionId> executionIds, DataLoaderRegistry dataLoaderRegistry) {
        super(dataLoaderRegistry);
        RequestStack requestStack = getStack();
        executionIds.forEach(requestStack::addExecution);
    }

    @Override
    public InstrumentationState createState(ExecutionId executionId) {
        if (!getStack().contains(executionId)) {
            throw new RuntimeException(String.format("Request tracking not set up with execution id %s", executionId));
        }
        return null;
    }

}