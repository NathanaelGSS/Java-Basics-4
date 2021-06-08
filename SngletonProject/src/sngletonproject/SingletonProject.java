
package sngletonproject;

/**
 *
 * @author Nathanael
 */
public class SingletonProject {

    private static volatile SingletonProject example;//singleton instance/example made here
    
    
    public static SingletonProject getExample() {//instance/example method
        if (example == null) {//single lock
            synchronized(SingletonProject.class) {//sync the lock
                if (example == null) {//double lock
                    example = new SingletonProject();//release the second lock
                    System.out.println("Example Output");
                }
            }
        }
        return example;//final return
    }
    
    
}
