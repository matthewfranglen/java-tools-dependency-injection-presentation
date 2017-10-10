import java.util.Arrays;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation {
    String value();
}

@MyAnnotation("class level")
public class AnnotationExample {

    @MyAnnotation("method level")
    private void example() {}

    public static void main(String[] args) throws Exception {
        Class<AnnotationExample> myClass = AnnotationExample.class;

        printAnnotations(myClass.getAnnotations());
        printAnnotations(myClass.getDeclaredMethod("example").getAnnotations());
    }

    private static void printAnnotations(Annotation[] annotations) {
        Arrays.stream(annotations)
            .forEach(System.out::println);
    }

}
