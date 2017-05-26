package com.mysquar.sample.movies.base;

import org.junit.After;
import org.junit.Before;
import org.mockito.MockitoAnnotations;

/**
 * Created by phannguyen on 5/25/17.
 */

public class BaseTest {
    @Before
    public void initialize() {
        MockitoAnnotations.initMocks(this);
//        Transformer.overrideComputationScheduler(Schedulers.immediate());
//        Transformer.overrideIoScheduler(Schedulers.immediate());
//        Transformer.overrideMainThreadScheduler(Schedulers.immediate());
//        Transformer.overrideNewThreadScheduler(Schedulers.immediate());
    }

    @After
    public void release() {
//        Transformer.resetIoScheduler();
//        Transformer.resetMainThreadScheduler();
//        Transformer.resetComputationScheduler();
//        Transformer.resetNewThreadScheduler();
    }
}
