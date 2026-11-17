
package student.management.system;


import java.util.Scanner;
import java.util.InputMismatchException;


class Student {
    protected int id;
    protected String name;
    protected float grade;
    protected Student next;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
        this.grade = 0.0f; 
        this.next = null;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Full name: " + name + " | GPA: " + grade;
    }
}

class UnsortedArray {
    protected Student[] array;
    protected int last, max;

    public UnsortedArray(int n) {
        max = n;
        array = new Student[max];
        last = -1;
    }

    public void insert(Student s) {
        if (last < max - 1) array[++last] = s;
    }

    public int search(int id) {
        for (int i = 0; i <= last; i++) {
            if (array[i].id == id) return i; 
        }
        return -1;
    }

    public Student removeAt(int index) {
        Student temp = array[index];
        array[index] = array[last]; 
        last--;
        return temp;
    }
}

class SortedList {
    protected Student head = null;

    public void insertSorted(Student s) {
        Student newNode = new Student(s.id, s.name);
        newNode.grade = s.grade;
        if (head == null || newNode.id < head.id) {
            newNode.next = head;
            head = newNode;
            return;
        }
        Student current = head;
        while (current.next != null && current.next.id < newNode.id) current = current.next;
        newNode.next = current.next;
        current.next = newNode;
    }

    public void remove(int id) {
        if (head == null) return;
        if (head.id == id) { head = head.next; return; }
        Student curr = head;
        while (curr.next != null && curr.next.id != id) curr = curr.next;
        if (curr.next != null) curr.next = curr.next.next;
    }

    public void displayAll() {
        Student temp = head;
        if (temp == null) System.out.println("No data in the system.");
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }
}

class UndoStack {
    protected Student[] stack;
    protected int top, max;

    public UndoStack(int n) {
        max = n;
        stack = new Student[max];
        top = -1;
    }

    public void push(Student s) {
        if (top < max - 1) {
            Student copy = new Student(s.id, s.name);
            copy.grade = s.grade;
            stack[++top] = copy;
        }
    }

    public Student pop() {
        return (top == -1) ? null : stack[top--];
    }
}

class WaitingQueue {
    protected class QueueNode {
        String name;
        QueueNode next;
        QueueNode(String n) { this.name = n; }
    }
    protected QueueNode front, rear;

    public void enqueue(String name) {
        QueueNode newNode = new QueueNode(name);
        if (rear == null) { front = rear = newNode; return; }
        rear.next = newNode;
        rear = newNode;
    }

    public String dequeue() {
        if (front == null) return null;
        String name = front.name;
        front = front.next;
        if (front == null) rear = null;
        return name;
    }

    public void displayQueue() {
        if (front == null) { System.out.println("No students in the waiting list."); return; }
        QueueNode temp = front;
        System.out.println("Students in queue :");
        while (temp != null) {
            System.out.println("- " + temp.name);
            temp = temp.next;
        }
    }
}

public class StudentManagementSystem {
    private static int idCounter = 1000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UnsortedArray rawData = new UnsortedArray(100);
        SortedList sortedData = new SortedList();
        UndoStack undoStack = new UndoStack(50);
        WaitingQueue waitQ = new WaitingQueue();

        while (true) {
            try {
                System.out.println("\n--- Home menu ---");
                System.out.println("1. Add new student\n2. Edit\n3. Queue\n4. Exit");
                System.out.print("Your choice: ");
                int mainChoice = sc.nextInt();
                sc.nextLine(); 

                if (mainChoice == 1) {
                    System.out.print("Please enter your full name: ");
                    String name = sc.nextLine();
                    Student s = new Student(++idCounter, name);
                    rawData.insert(s);
                    sortedData.insertSorted(s);
                    System.out.println("Signed up with ID : " + s.id);

                } else if (mainChoice == 2) {
                    processEditMenu(sc, rawData, sortedData, undoStack);
                } else if (mainChoice == 3) {
                    processWaitMenu(sc, rawData, sortedData, waitQ);
                } else if (mainChoice == 4) {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter the option number only, not text!");
                sc.nextLine(); 
            }
        }
    }

    private static void processEditMenu(Scanner sc, UnsortedArray raw, SortedList sorted, UndoStack stack) {
        int choice = 0;
        do {
            try {
                System.out.println("\n-- Edit list --\n1.  View\n2. Update average\n3. delete\n4. Revert \n5. Exit");
                System.out.print("Your choice: ");
                choice = sc.nextInt();
                if (choice == 1) sorted.displayAll();
                else if (choice == 2) {
                    System.out.print("Student ID: "); int id = sc.nextInt();
                    int loc = raw.search(id);
                    if (loc != -1) {
                        stack.push(raw.array[loc]);
                        System.out.print("New average: ");
                        raw.array[loc].grade = sc.nextFloat();
                        sorted.remove(id);
                        sorted.insertSorted(raw.array[loc]);
                    }
                } else if (choice == 3) {
                    System.out.print("ID to delete : "); int id = sc.nextInt();
                    int loc = raw.search(id);
                    if (loc != -1) {
                        stack.push(raw.array[loc]);
                        sorted.remove(id);
                        raw.removeAt(loc);
                    }
                } else if (choice == 4) {
                    Student back = stack.pop();
                    if (back != null) { raw.insert(back); sorted.insertSorted(back);
                     System.out.println("Undo completed and student restored: " + back.name);}
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Returning to the menu...");
                sc.nextLine();
            }
        } while (choice != 5);
    }

    private static void processWaitMenu(Scanner sc, UnsortedArray raw, SortedList sorted, WaitingQueue q) {
        int choice = 0;
        do {
            try {
                System.out.println("\n-- Waiting list--\n1. Add to waiting list\n2. Add to record\n3. View\n4. Exit");
                System.out.print("Your choice: ");
                choice = sc.nextInt();
                sc.nextLine();
                if (choice == 1) {
                    System.out.print("Student Name: "); q.enqueue(sc.nextLine());
                } else if (choice == 2) {
                    String name = q.dequeue();
                    if (name != null) {
                        Student s = new Student(++idCounter, name);
                        raw.insert(s); sorted.insertSorted(s);
                        System.out.println("Added to the record");
                    }
                } else if (choice == 3) q.displayQueue();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Returning to the menu...");
                sc.nextLine();
            }
        } while (choice != 4);
    }
}
