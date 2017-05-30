package mysquar.com.sample.movies.data.apiws.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by phannguyen on 5/25/17.
 */
@Database(entities = {EMovie.class}, version = 1)
public abstract class AppDB extends RoomDatabase{
    //private static AppDB INSTANCE;
    //private static Context _context;

    public abstract MovieDAO movieDao();

    //public static AppDB getDatabase() {
    //    if (INSTANCE == null) {
    //        INSTANCE =
    //                Room.databaseBuilder(_context.getApplicationContext(),
    //                        AppDB.class, "movies_db").build();
    //
    //    }
    //    return INSTANCE;
    //}
    //
    //public static void destroyInstance() {
    //    INSTANCE = null;
    //}
    //
    //public static void init(Context context) {
    //    _context = context;
    //}

}
