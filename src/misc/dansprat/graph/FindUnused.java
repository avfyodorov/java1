package ru.progwards.java2.lessons.graph;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.*;
import java.util.List;

public class FindUnused {

    private static void DFS(CObject object){
        object.mark = 1;
        for (CObject child: object.references){
            if (child.mark == 0){
                DFS(child);
            }
        }
        object.mark = 2;
    }

    public static List<CObject> find(List<CObject> roots, List<CObject> objects){
        for (CObject root:roots){
            if (root.mark ==0){
                DFS(root);
            }
        }
        List <CObject> unusedObjects = new ArrayList<>();
        for (CObject object:objects){
            if (object.mark == 0){
                unusedObjects.add(object);
            }
        }
        return unusedObjects;
    }


    public static void main(String[] args) {

        CObject root1 = new CObject("Root 1");
        CObject root2 = new CObject("Root 2");
        ArrayList <CObject> roots = new ArrayList<>();
        ArrayList <CObject> allObjects = new ArrayList<>();
        ArrayList <CObject> other = new ArrayList<>();
        roots.add(root1);
        roots.add(root2);
        allObjects.add(root1);
        allObjects.add(root2);
        for (int i =0;i<13;i++){
            other.add(new CObject(String.valueOf(i)));
        }
        allObjects.addAll(other);

        root1.references = Arrays.asList(other.get(0));
        root2.references = Arrays.asList(other.get(5));
        other.get(0).references = Arrays.asList(other.get(1),other.get(2),other.get(3));
        other.get(1).references = Arrays.asList(other.get(4));
        other.get(2).references = Arrays.asList(other.get(3));

        other.get(5).references = Arrays.asList(other.get(7));
        other.get(7).references = Arrays.asList(other.get(6),other.get(8),other.get(9));
        other.get(8).references = Arrays.asList(other.get(9));

        List <CObject> unusedObjects = find(roots,allObjects);
        for(CObject object: unusedObjects){
            System.out.print(object.info+" ");
        }

        URL iconURL = ClassLoader.getSystemResource("Graph.png");
        Icon icon = new ImageIcon(iconURL);
        JOptionPane.showMessageDialog(null,null,null,JOptionPane.INFORMATION_MESSAGE,icon);

    }
}
