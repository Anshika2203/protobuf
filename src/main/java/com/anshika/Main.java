package com.anshika;

import com.anshika.proto.Student;
import com.anshika.proto.Students;
import com.google.protobuf.InvalidProtocolBufferException;

public class Main {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        byte[] dataToSend = sender();
        receiver(dataToSend);
    }

    private static void receiver(byte[] dataFromSend) throws InvalidProtocolBufferException {
        System.out.println("data received by me = ");
        for(int i=0; i<dataFromSend.length; i++){
            System.out.print(dataFromSend[i]+" ");
        }
        System.out.println();

        Students students = Students.parseFrom(dataFromSend);
        System.out.println(students.getStudentsList().size());
        System.out.println(students.getStudents(0).getName()+" "+ students.getStudents(0).getAge());
        System.out.println(students.getStudents(1).getName()+" "+ students.getStudents(1).getAge());
    }

    private static byte[] sender() {
        Student student1 = Student.newBuilder().setAge(10).setName("Anshika").build();
        Student student2 = Student.newBuilder().setAge(11).setName("Anand").build();

        Students students = Students.newBuilder().addStudents(student1).addStudents(student2).build();
        byte[] arrayTosend = students.toByteArray();
        for(int i=0; i<arrayTosend.length; i++){
            System.out.println(arrayTosend[i]+" ");
        }
        System.out.println();
        System.out.println(arrayTosend.length);
        return arrayTosend;
    }
}

// protoc -I=proto --java_out=src/main/java proto/Student.proto