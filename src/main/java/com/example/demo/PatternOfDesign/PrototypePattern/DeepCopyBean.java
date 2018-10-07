package com.example.demo.PatternOfDesign.PrototypePattern;

import java.io.*;

public class DeepCopyBean implements Serializable {

    private String objectField;
    private int primitiveField;

    public String getObjectField() {
        return objectField;
    }

    public void setObjectField(String objectField) {
        this.objectField = objectField;
    }

    public int getPrimitiveField() {
        return primitiveField;
    }

    public void setPrimitiveField(int primitiveField) {
        this.primitiveField = primitiveField;
    }

    public DeepCopyBean deepCopy(){
        try{
            ByteArrayOutputStream buf = new ByteArrayOutputStream();
            ObjectOutputStream o = new ObjectOutputStream(buf);
            o.writeObject(this);

            ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(buf.toByteArray()));

            return (DeepCopyBean) in.readObject();
        }catch (IOException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        return null;
    }


    public static void main(String[] args) {
        DeepCopyBean originalBean = new DeepCopyBean();
        originalBean.setObjectField(new String("123456"));
        originalBean.setPrimitiveField(2);

        DeepCopyBean newBean = originalBean.deepCopy();

        //int为原始类型 == 比较为true
        System.out.println("Primitive == ? "+(newBean.getPrimitiveField() == originalBean.getPrimitiveField()));
        //object为引用类型 == 比较为false
        System.out.println("Object == ? "+(newBean.getObjectField() == originalBean.getObjectField()));
        //使用equals 比较对象引用的内容是否相同  结果为true
        System.out.println("Object equal ?"+ (newBean.getObjectField().equals(originalBean.getObjectField())));
    }
}
