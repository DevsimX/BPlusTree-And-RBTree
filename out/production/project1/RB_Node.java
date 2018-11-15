public class RB_Node {
    Boolean color;
    RB_Node leftNode;
    RB_Node rightNode;
    RB_Node parentNode;
    String key;
    String value;

    RB_Node() {
        this.color = false;
    }

    RB_Node(String key) {
        this.color = false;
        this.key = key;
    }

    RB_Node(String key , String value){
        this.color = false;
        this.key = key;
        this.value = value;
    }

    String getColor(){
        if(!this.color){
            return "Black";
        }else {
            return "Red";
        }
    }
}