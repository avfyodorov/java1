package misc.ansemal.classloader;

/*
import javassist.*;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class ProfileTransformer implements ClassFileTransformer {
   String [] classes; // массив имен классов взятых из параметра агента

   ProfileTransformer (String[] classes) {
      this.classes = classes;
   }

   @Override
   public byte[] transform(ClassLoader loader,
                           String className,
                           Class<?> classBeingRedefined,
                           ProtectionDomain protectionDomain,
                           byte[] classfileBuffer) throws IllegalClassFormatException {

      String classNameWithDot = className.replace('/', '.');
      for (int i =0; i<classes.length; i++)
         // если загружаемый класс из нужных нам
         if (classNameWithDot.equals(classes[i])) {
            ClassPool cp = ClassPool.getDefault();
            cp.importPackage("ru.progwards.java2.lessons.classloader");
            cp.importPackage("java.io");
            try {
               CtClass ct = cp.get(classNameWithDot);
               CtMethod [] ctMethods = ct.getDeclaredMethods();
               for (CtMethod ctMethod:ctMethods) {
                  ctMethod.insertBefore("Profiler.enterSection(\"" + ctMethod.getLongName()+ "\");");
                  // если это main метод главного класса
                  if (i == 0 && ctMethod.getName().endsWith("main")) {
                     // создаем метод printStatisticInfo
                     ct.addMethod(CtNewMethod.make("public static void printStatisticInfo(String filename) {" +
                             "java.util.List itog = Profiler.getStatisticInfo();" +
                             "FileWriter writer = new FileWriter(filename + \".stat\", true);" +
                             "try { " +
                             "for (int i = 0; i<itog.size(); i++) { " +
                             "writer.write(itog.get(i).toString() + \"\\n\");" +
                             "}" +
                             "} catch (IOException e) {" +
                             "System.out.println(e.getMessage());" +
                             "} finally {" +
                             "writer.close();" +
                             "}" +
                             "}", ct));
                     ctMethod.insertAfter("{Profiler.exitSection(\"" + ctMethod.getLongName() + "\"); printStatisticInfo(\"" + ctMethod.getName() + "\");  }");//\"" + ctMethod.getLongName() + ".stat\")}");
                  } else
                     ctMethod.insertAfter("Profiler.exitSection(\"" + ctMethod.getLongName() + "\");");
               }
               return ct.toBytecode();
            } catch (NotFoundException | CannotCompileException | IOException e) {
               e.printStackTrace();
            }
         }
      return classfileBuffer;
   }
}

*/