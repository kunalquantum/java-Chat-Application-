import com.sun.security.jgss.GSSUtil;

import javax.naming.ldap.SortKey;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.border.*;
import java.net.*;

public class client implements ActionListener {
    static DataOutputStream dout;
    static JPanel ai;
    JTextField en;
    //    creating the verticle box for the messages display
    static Box verical=Box.createVerticalBox();
    static JFrame f=new JFrame();
    client(){
//        preventing the use of default layout
        f.setLayout(null);
//        creating the header using panel
        JPanel p1=new JPanel();
        p1.setBackground(new Color(23,34,56));
//        setting the panel in the top of the frame
        p1.setBounds(0,0,450,70);
        f.add(p1);

        ImageIcon li=new ImageIcon(ClassLoader.getSystemResource("icons/arr.png"));
        Image i2=li.getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT);

        ImageIcon i3=new ImageIcon(i2);
        JLabel back = new JLabel(i3);
        back.setBounds(5,20,25,25);
        p1.setLayout(null);

        p1.add(back);
//        adding the event handling
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.exit(0);// this will basically close the present window
            }
        });

//for profile photo
        ImageIcon l3=new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
        Image i4=l3.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT);

        ImageIcon i6=new ImageIcon(i4);
        JLabel pp = new JLabel(i6);
        pp.setBounds(40,10,50,50);
        p1.setLayout(null);
        p1.add(pp);
//for another image video icon
//        ImageIcon l4=new ImageIcon(ClassLoader.getSystemResource("icons/v1.png"));
//        Image i7=l4.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT);
//
//        ImageIcon i8=new ImageIcon(i7);
//        JLabel video = new JLabel(i8);
//        video.setBounds(50,10,50,50);
//        p1.setLayout(null);

//        p1.add(video);
//another image of the call
        ImageIcon l5=new ImageIcon(ClassLoader.getSystemResource("icons/p2.png"));
        Image i10=l5.getImage().getScaledInstance(30,35,Image.SCALE_DEFAULT);

        ImageIcon i11=new ImageIcon(i10);
        JLabel phone = new JLabel(i11);
        phone.setBounds(350,10,30,35);
        p1.setLayout(null);

        p1.add(phone);

        ImageIcon l6=new ImageIcon(ClassLoader.getSystemResource("icons/dots.png"));
        Image i13=l6.getImage().getScaledInstance(30,35,Image.SCALE_DEFAULT);

        ImageIcon i14=new ImageIcon(i13);
        JLabel dots = new JLabel(i14);
        dots.setBounds(400,10,30,35);
        p1.setLayout(null);

        p1.add(dots);

//we will be using the jlabel for the putting the name on the frame
        JLabel name=new JLabel("Mrs Cat");
        name.setBounds(110,15,100,30);
        name.setForeground(Color.WHITE);
        name.setFont(new Font(Font.SANS_SERIF,Font.BOLD,25));
        p1.add(name);
//status
        JLabel status=new JLabel("Active Now");
        status.setBounds(110,45,100,30);
        status.setForeground(Color.blue);
        status.setFont(new Font(Font.SANS_SERIF,Font.CENTER_BASELINE,10));
        p1.add(status);

//chatting panel
        ai=new JPanel();
        ai.setBounds(5,75,435,630);

        ai.setBackground(new Color(23,34,56));
        f.add(ai);

//        allowing user to enter the text
        en=new JTextField();
        en.setBounds(5,655,310,40);
        en.setFont(new Font(Font.MONOSPACED,Font.ITALIC,16));
        en.setBackground(Color.BLUE);
        en.setForeground(Color.white);

        f.add(en);

//adding the send button
        JButton but=new JButton("Send");
        but.setBounds(320,655,100,40);
        but.setBackground(new Color(78,50,56));
        but.setForeground(Color.black);

//        adding the event listener
        but.addActionListener(this);
        en.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,16));


        f.add(but);


        f.setSize(450,700);

//        left top is the origin
//        set location
        f.setLocation(800,50);
//        removing the panel
        f.setUndecorated(true);
//        changing the backgroud color
        f.getContentPane().setBackground(new Color(0,0,51));
//layouting


        f.setVisible(true);


    }
    public void actionPerformed(ActionEvent ae){
        try{
//    retrieving the text or the value from the textfeild
        String text=en.getText();
//        we are using the Border layout because it sets the content to the top , bottom, left or right of the panel and we want to place the send text at the right
        ai.setLayout(new BorderLayout());

        JPanel p2=formatLabel(text);
        p2.setBackground(new Color(0,0,51));

// creatng a new pannel
        JPanel right=new JPanel(new BorderLayout());
        right.setBackground(new Color(23,34,56));
        right.add(p2,BorderLayout.LINE_END);
        verical.add(right);
        verical.add(Box.createHorizontalStrut(10));
        ai.add(verical,BorderLayout.PAGE_START);
//        sending the message protocol
        dout.writeUTF(text);



        en.setText("");

        f.repaint();
        f.invalidate();
        f.validate();
    }catch(Exception e){
        e.printStackTrace();
    }}
    public static JPanel formatLabel(String text){
        JPanel panel=new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        JLabel output=new JLabel(text);
        output.setFont(new Font("Tahoma",Font.PLAIN,16));
        output.setBackground(new Color(37,211,102));
        output.setForeground(Color.BLUE);
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(15,15,15,50));
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.add(output);
//      to show the time and data of message
        Calendar c=Calendar.getInstance();
        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
        JLabel time=new JLabel();//FOR TIME WE HAVE THIS
        time.setForeground(Color.white);
        time.setText(sdf.format(c.getTime()));
        panel.add(time);

        return panel;

    }

    public static void main(String[] args) {

        new client();
//        Socket programming for the client

        try{
            Socket s=new Socket("localhost",4999);
            DataInputStream din=new DataInputStream(s.getInputStream());
            dout=new DataOutputStream(s.getOutputStream());
                while(true){
                    ai.setLayout(new BorderLayout());
    //                    reading the input
                    String msg= din.readUTF();
                    JPanel panel=formatLabel(msg);
                    panel.setBackground(new Color(23,34,56));
                    JPanel left=new JPanel(new BorderLayout());
                    left.add(panel,BorderLayout.LINE_START);
                    left.setBackground(new Color(23,34,56));
                    verical.add(left);

                    verical.add(Box.createVerticalStrut(15));
                    ai.add(verical,BorderLayout.PAGE_START);

                    f.validate();
                }


            }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
