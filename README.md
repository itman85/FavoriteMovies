# FavoriteMovies
This is simple app load favourite movies from themoviedb.org. User can endless loading movies.
# Tech stacks
- This demo apply Clean architecture, separate app into three layers: app,data,domain.
- In app layer apply mvvp pattern, data binding between view (xml layout) and viewmodel
- Data layer apply room component as local storage, retrofit 2 as rest client
- Using rxjava across layers.
- Using Dragger 2 as DI
- Unit testing use mockito and robolectric.
- Jacoco for test coverage report
