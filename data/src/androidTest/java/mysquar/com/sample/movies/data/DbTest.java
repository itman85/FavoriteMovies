package mysquar.com.sample.movies.data;

import org.junit.After;
import org.junit.Before;

import android.arch.persistence.room.Room;
import android.support.annotation.CallSuper;
import android.support.test.InstrumentationRegistry;

import mysquar.com.sample.movies.data.apiws.db.AppDB;

/**
 * Created by phannguyen on 5/30/17.
 */

public abstract class DbTest {

  protected AppDB mAppDB;

  @CallSuper
  @Before
  public void setUp() {
    mAppDB = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
        AppDB.class).build();
  }

  @CallSuper
  @After
  public void tearDown() {
    mAppDB.close();
  }
}
