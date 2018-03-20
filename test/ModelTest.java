import org.junit.Test;

import static play.test.Helpers.*;

/**
 * Check the model implementation.
 * @author Philip Johnson
 */
public class ModelTest {

  /**
   * Create and save an instance and see if it can be retrieved.
   * NOTE: Running this test within IntelliJ 14.1 fails with a PersistenceException.
   * Use 'activator test' for correct operation.
   */   
    @Test
    public void test() {
      running(fakeApplication(inMemoryDatabase()), new Runnable() {
            public void run() {
              /*PageRetrieval newStamp = new PageRetrieval();
              newStamp.save();
              assertThat(PageRetrieval.find().all().size()).isEqualTo(1);*/
            }
        });
    }
  
}
