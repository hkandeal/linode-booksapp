package org.abvijay.bozobooklibrary.booklibraryservice;

import io.micrometer.core.instrument.MeterRegistry;
import io.quarkus.panache.common.Parameters;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.opentracing.Traced;
import org.jboss.logging.Logger;

@Traced
@Path("/")
public class BookLibraryService {

    private static final Logger LOG = Logger.getLogger(BookLibraryService.class);

    @Inject
    MeterRegistry registry;

    @GET
    @Path("get/{userId}")
    @Produces(APPLICATION_JSON)
    public List<BookLibrary> getBooksforUser(@PathParam("userId") String userId) {
        LOG.info("Get Books for User:" + userId);
        List<BookLibrary> books = BookLibrary.find("userID", userId).list();
        LOG.info("Returned Books:" + books.toString());
        return books;
    }

    @POST
    @Path("add/{userid}/{bookid}")
    @Transactional
    public Response addBook(@PathParam("userid") String userId, @PathParam("bookid") String bookId) {
        try {
            BookLibrary book = new BookLibrary();
            LOG.info("Adding Book UserId " + userId + " bookId " + bookId);
            book.setBookID(bookId);
            book.setUserID(userId);
            //System.out.println("UserId " + book.getUserID() + " bookId " + book.getBookID());
            book.persist();
            return Response.ok("success", MediaType.TEXT_PLAIN).build();
        } catch (Exception e) {
            Response responseJson = Response.serverError().type(MediaType.APPLICATION_JSON)
                    .entity("{" + e.getMessage() + "}").build();
            LOG.error("Error: " + responseJson);
            return responseJson;
        }
    }

    @POST
    @Path("delete/{userid}/{bookid}")
    @Transactional
    public Response deleteBook(@PathParam("userid") String userId, @PathParam("bookid") String bookId) {
        try {

            LOG.info("Deleting UserId " + userId + " bookId " + bookId);
            // BookLibrary book = new BookLibrary();
            // Map<String, String> params = new HashMap<>();
            // params.put("userID", userId);
            // params.put("bookID", bookId);
            // book.setBookID(bookId);
            // book.setUserID(userId);
            // book.delete();

            BookLibrary.delete("delete from BookLibrary pl where pl.bookID =  ?1", bookId);

            return Response.ok("success", MediaType.TEXT_PLAIN).build();
        } catch (Exception e) {
            Response responseJson = Response.serverError().type(MediaType.APPLICATION_JSON)
                    .entity("{" + e.getMessage() + "}").build();
            LOG.error("Error: " + responseJson);
            return responseJson;
        }
    }
}
