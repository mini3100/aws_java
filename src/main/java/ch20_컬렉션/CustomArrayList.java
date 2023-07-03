package ch20_컬렉션;

public class CustomArrayList<T> {
    private String[] array;

    public CustomArrayList() {
        array = new String[0];
    }

    public void add(String str) {
        String[] newArray = new String[array.length + 1];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        newArray[array.length] = str;
        array = newArray;
    }

    //오버로딩
    public void add(int index, String str) {
        String[] newArray = new String[array.length + 1];
        for (int i = 0; i < index; i++) {
            newArray[i] = array[i];
        }
        newArray[index] = str;
        for (int i = index; i < array.length; i++) {
            newArray[i + 1] = array[i];
        }
        array = newArray;
    }

    public String remove() {
        String value = array[array.length - 1];
        String[] newArray = new String[array.length - 1];
        for (int i = 0; i < array.length - 1; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
        return value;
    }

    public String remove(int index) {
        String value = array[index];
        String[] newArray = new String[array.length - 1];
        //방법 1
        for (int i = 0; i < index; i++) {
            newArray[i] = array[i];
        }
        for (int i = index + 1; i < array.length; i++) {
            newArray[i - 1] = array[i];
        }
        //방법 2
        /*for (int i = 0; i < array.length; i++) {
            if (i < index) newArray[i] = array[i];
            else if (i == index) continue;
            else newArray[i - 1] = array[i];
        }*/
        array = newArray;
        return value;
    }

    public void set(int index, String str){
        array[index] = str;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (String str : array) {
            builder.append(str + ", ");
        }
        builder.delete(builder.lastIndexOf(", "), builder.length());
        builder.append("]");

        return builder.toString();
    }
}
