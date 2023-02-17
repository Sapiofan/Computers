#Computers

In fact, software works as web one, but without frontend.

###Configuration
For proper work of it, it should be connected to database. 

After that run [`V1__init.sql`](src/main/resources/db.migration/V1__init.sql). Now tests can be run.

It's also possible to work without sql script.

###Logic of software

User opens page with catalog of computers ("/"), he can find some product with help of "search engine" and
request ("/computer" with parameter NAME) and find products associated with name or brand of it.
After that, user can open the "page" of product ("/{id}) and see details of this computer.
If he likes it, he can buy it through filling the form with some data ("/order"). If transaction is successful,
note about bought product will be added to db.

