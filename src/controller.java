/**
 * Sample Skeleton for 'Untitled.fxml' Controller Class
 */

import java.io.*;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class controller{
    private RB_Tree rb_tree = new RB_Tree();
    private BplusTree<String , String> bPlusTree = new BplusTree<>(4);

    @FXML // fx:id="label"
    private Label label; // Value injected by FXMLLoader

    @FXML // fx:id="showAll"
    private Button showAll; // Value injected by FXMLLoader

    @FXML // fx:id="path"
    private TextField path; // Value injected by FXMLLoader

    @FXML // fx:id="submit"
    private Button submit; // Value injected by FXMLLoader

    @FXML // fx:id="english"
    private TextField english; // Value injected by FXMLLoader

    @FXML // fx:id="chinese"
    private TextField chinese; // Value injected by FXMLLoader

    @FXML // fx:id="add"
    private Button add; // Value injected by FXMLLoader

    @FXML // fx:id="delete"
    private Button delete; // Value injected by FXMLLoader

    @FXML // fx:id="rb_bt"
    private RadioButton rb_bt; // Value injected by FXMLLoader

    @FXML // fx:id="bp_bt"
    private RadioButton bp_bt; // Value injected by FXMLLoader

    @FXML // fx:id="search"
    private TextField search; // Value injected by FXMLLoader

    @FXML // fx:id="translate"
    private Button translate; // Value injected by FXMLLoader

    @FXML // fx:id="start"
    private TextField start; // Value injected by FXMLLoader

    @FXML // fx:id="end"
    private TextField end; // Value injected by FXMLLoader

    @FXML // fx:id="rangeSearch"
    private Button rangeSearch; // Value injected by FXMLLoader

    @FXML
    void submit(){
        submit.setOnAction(e ->{
            String filePath = path.getText();

            if(filePath.equals("1_initial.txt")){
                initial();
            }else if(filePath.equals("2_delete.txt")){
                delete();
            }else if(filePath.equals("3_insert.txt")){
                insert();
            }else
                label.setText("No such file");
        });
    }
    //Called to implement read the file and implement the according operation

    @FXML
    private void initial(){
        File file = new File("./src/1_initial.txt");
        if(rb_bt.isSelected()){
            try{
                String key,value;
                InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "GBK");
                BufferedReader br = new BufferedReader(isr);
                int line = 0;
                br.readLine();
                //Read the first line , to read the data behind it.

                while((key=br.readLine()) != null && (value=br.readLine()) != null){
                    //Read two data each time
                    rb_tree.insert(rb_tree , new RB_Node(key,value));
                    //Insert the data use tree's insert function
                    line+=2;
                    if(line%200 == 0){
                        if(line <= 1000){
                            System.out.println("RBTree's preOrderPrint:");
                            rb_tree.preOrderPrint(rb_tree);
                            System.out.println("The tree's current line is " + line/2);
                        }

                    }//Print the result after the insertion
                }
                isr.close();
                br.close();
            }catch (IOException ioe){
                ioe.printStackTrace();
            }finally {
                label.setText("Initial successfully");
            }
        }else {
            try {
                String key,value;
                InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "GBK");
                BufferedReader br = new BufferedReader(isr);
                int line = 0;
                br.readLine();
                //Read the first line , to read the data behind it.
                while((key=br.readLine()) != null && (value=br.readLine()) != null){
                    //Read two data each time
                    bPlusTree.insertOrUpdate(key,value);
                    //Insert the data use tree's insert function
                    line+=2;
                    if(line%200 == 0){
                        //Add the time of insert into the arrayList time2
                        if(line <= 1000){
                            System.out.println("BPlusTree's preOrderPrint:");
                            bPlusTree.preOrderPrint(bPlusTree);
                            System.out.println("The tree's current line is " + line/2);
                        }
                        //Update the beforeTime
                    }//Print the result after the insertion
                }
                isr.close();
                br.close();
            }catch (IOException ioe){
                ioe.printStackTrace();
            }finally {
                label.setText("Initial successfully");
            }
        }
    }
    //Initial the tree
    @FXML
    private void delete(){
        File file = new File("./src/2_delete.txt");
        try {
            if(rb_bt.isSelected()) {
                String key;
                InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "GBK");
                BufferedReader br = new BufferedReader(isr);
                br.readLine();
                //Read the first line , to read the data behind it.

                while((key=br.readLine()) != null){
                    //Read one data each time
                    rb_tree.delete(rb_tree , rb_tree.search(rb_tree.rootNode,key));
                    //Delete the data use tree's delete function
                }
                isr.close();
                br.close();
            }
            else {
                String key;
                InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "GBK");
                BufferedReader br = new BufferedReader(isr);
                br.readLine();
                //Read the first line , to read the data behind it.

                while((key=br.readLine()) != null){
                    //Read one data each time
                    bPlusTree.remove(key);
                    //Delete the data use tree's delete function
                }
                isr.close();
                br.close();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }finally {
            label.setText("Delete successfully");
        }
    }
    //Delete some data

    @FXML
    private void insert(){
        File file = new File("./src/3_insert.txt");
        try {
            if(rb_bt.isSelected()) {
                String key,value;
                InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "GBK");
                BufferedReader br = new BufferedReader(isr);
                br.readLine();
                //Read the first line , to read the data behind it.

                while((key=br.readLine()) != null && (value=br.readLine()) != null){
                    //Read two data each time
                    rb_tree.insert(rb_tree , new RB_Node(key,value));
                    //Insert the data use tree's insert function
                }
                isr.close();
                br.close();
            }
            else {
                String key,value;
                InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "GBK");
                BufferedReader br = new BufferedReader(isr);
                int line = 0;
                br.readLine();
                //Read the first line , to read the data behind it.

                while((key=br.readLine()) != null && (value=br.readLine()) != null){
                    //Read two data each time
                    bPlusTree.insertOrUpdate(key,value);
                    //Insert the data use tree's insert function
                }
                isr.close();
                br.close();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            label.setText("Insert successfully");
        }
    }
    //Insert some data

    @FXML
    void showAll(){
        showAll.setOnAction(e ->{
            Stage showallStage = new Stage();
            TextArea ta = null;
            if(rb_bt.isSelected()) {
                ta = new TextArea(rb_tree.getTree());
            }
            else {
                ta = new TextArea(bPlusTree.getTree(bPlusTree));
            }
            showallStage.setScene(new Scene(ta, 400,500));
            showallStage.show();
        });
    }

    @FXML
    void add(){
        add.setOnAction(e ->{
            String english = this.english.getText(), chinese = this.chinese.getText();
            if(english == null || english.equals(""))
                label.setText("input English word please");
            else if(chinese == null || chinese.equals(""))
                label.setText("input Chinese meaning please");
            else {
                if(rb_bt.isSelected()) {
                    rb_tree.insert(rb_tree , new RB_Node(english , chinese));
                }
                else
                    bPlusTree.insertOrUpdate(english,chinese);
                label.setText("insert successfully");
            }
        });
    }

    @FXML
    void deleteOne(){
        delete.setOnAction(e ->{
            String english = this.english.getText();
            if(english == null || english.equals(""))
                label.setText("input English word please");
            if(rb_bt.isSelected())
                rb_tree.delete(rb_tree , rb_tree.search(rb_tree.rootNode , english));
            else
                bPlusTree.remove(english);
            label.setText("delete successfully");
        });
    }

    @FXML
    void translate (){
        translate.setOnAction(e -> {
            String s = this.search.getText();
            if(rb_bt.isSelected()) {
                String t = rb_tree.search(rb_tree.rootNode , s).value;
                if(t == null)
                    label.setText("no such word");
                else
                    label.setText(t);
            }
            else {
                String t = bPlusTree.get(s);
                if(t != null)
                    label.setText(t);
                else
                    label.setText("no such word");
            }
        });
    }

    @FXML
    void rangeSearch(){
        rangeSearch.setOnAction(e -> {
            String st = start.getText(),
                    ed = end.getText();
            if(rb_bt.isSelected()){
                if(st.compareTo(ed) > 0)
                    label.setText("the start word is behind the end word");
                else {
                    Stage newStage = new Stage();
                    String t = rb_tree.rangeSearch(st,ed);
                    newStage.setScene(new Scene(new TextArea(t), 300, 400));
                    newStage.showAndWait();
                }
            }else {
                if(st.compareTo(ed) > 0)
                    label.setText("the start word is behind the end word");
                else {
                    Stage newStage = new Stage();
                    String t = bPlusTree.rangeSearch(st,ed);
                    newStage.setScene(new Scene(new TextArea(t), 300, 400));
                    newStage.showAndWait();
                }
            }
        });
    }
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert path != null : "fx:id=\"path\" was not injected: check your FXML file 'Untitled.fxml'.";
        assert submit != null : "fx:id=\"submit\" was not injected: check your FXML file 'Untitled.fxml'.";
        assert english != null : "fx:id=\"english\" was not injected: check your FXML file 'Untitled.fxml'.";
        assert chinese != null : "fx:id=\"chinese\" was not injected: check your FXML file 'Untitled.fxml'.";
        assert add != null : "fx:id=\"add\" was not injected: check your FXML file 'Untitled.fxml'.";
        assert delete != null : "fx:id=\"delete\" was not injected: check your FXML file 'Untitled.fxml'.";
        assert rb_bt != null : "fx:id=\"rb_bt\" was not injected: check your FXML file 'Untitled.fxml'.";
        assert bp_bt != null : "fx:id=\"bp_bt\" was not injected: check your FXML file 'Untitled.fxml'.";
        assert search != null : "fx:id=\"search\" was not injected: check your FXML file 'Untitled.fxml'.";
        assert translate != null : "fx:id=\"translate\" was not injected: check your FXML file 'Untitled.fxml'.";
        assert start != null : "fx:id=\"start\" was not injected: check your FXML file 'Untitled.fxml'.";
        assert end != null : "fx:id=\"end\" was not injected: check your FXML file 'Untitled.fxml'.";
        assert rangeSearch != null : "fx:id=\"rangeSearch\" was not injected: check your FXML file 'Untitled.fxml'.";

    }
}
