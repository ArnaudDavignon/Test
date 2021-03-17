/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test_ant;

import java.util.Calendar;
import java.util.GregorianCalendar;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author addav
 */
public class TEST_ANT extends Application{
    
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        // Create a clock and a label
        ClockPane clock = new ClockPane();
        String timeString = clock.getHour() + ":" + clock.getMinute() 
          + ":" + clock.getSecond();
        Label lblCurrentTime = new Label(timeString);

        // Place clock and label in border pane
        BorderPane pane = new BorderPane();
        pane.setCenter(clock);
        pane.setBottom(lblCurrentTime);
        BorderPane.setAlignment(lblCurrentTime, Pos.TOP_CENTER);

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 250, 250);
        primaryStage.setTitle("DisplayClock"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage   
    }
  
  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
  
  public class ClockPane extends Pane{
    private int hour;
    private int minute;
    private int second;
  
    /** Construct a default clock with the current time*/
    public ClockPane() {
      setCurrentTime();
    }

    /** Construct a clock with specified hour, minute, and second */
    public ClockPane(int hour, int minute, int second) {
      this.hour = hour;
      this.minute = minute;
      this.second = second;
    }

    /** Return hour */
    public int getHour() {
      return hour;
    }

    /** Set a new hour */
    public void setHour(int hour) {
      this.hour = hour;
      paintClock();
    }

    /** Return minute */
    public int getMinute() {
      return minute;
    }

    /** Set a new minute */
    public void setMinute(int minute) {
      this.minute = minute;
      paintClock();
    }

    /** Return second */
    public int getSecond() {
      return second;
    }

    /** Set a new second */
    public void setSecond(int second) {
      this.second = second;
      paintClock();
    }

    /* Set the current time for the clock */
    public void setCurrentTime() {
      // Construct a calendar for the current date and time
      Calendar calendar = new GregorianCalendar();

      // Set current hour, minute and second
      this.hour = calendar.get(Calendar.HOUR_OF_DAY);
      this.minute = calendar.get(Calendar.MINUTE);
      this.second = calendar.get(Calendar.SECOND);

      paintClock(); // Repaint the clock
    }

    /** Paint the clock */
    private void paintClock() {
      // Initialize clock parameters
      double clockRadius = Math.min(getWidth(), getHeight()) * 0.8 * 0.5;
      double centerX = getWidth() / 2;
      double centerY = getHeight() / 2;

      // Draw circle
      Circle circle = new Circle(centerX, centerY, clockRadius);
      circle.setFill(Color.WHITE);
      circle.setStroke(Color.BLACK);

      //Numbers
      Text t1 = new Text(centerX+clockRadius*Math.sin(Math.PI/6)-10, centerY-clockRadius*Math.cos(Math.PI/6)+18, "1");
      Text t2 = new Text(centerX+clockRadius*Math.sin(Math.PI/6*2)-17, centerY-clockRadius*Math.cos(Math.PI/6*2)+12, "2");
      Text t3 = new Text(centerX+clockRadius-18, centerY+4, "3");
      Text t4 = new Text(centerX+clockRadius*Math.sin(Math.PI/6*4)-17, centerY-clockRadius*Math.cos(Math.PI/6*4)-3, "4");
      Text t5 = new Text(centerX+clockRadius*Math.sin(Math.PI/6*5)-10, centerY-clockRadius*Math.cos(Math.PI/6*5)-10, "5");
      Text t6 = new Text(centerX-3, centerY+clockRadius-12, "6");
      Text t7 = new Text(centerX+clockRadius*Math.sin(Math.PI/6*7)+5, centerY-clockRadius*Math.cos(Math.PI/6*7)-10, "7");
      Text t8 = new Text(centerX+clockRadius*Math.sin(Math.PI/6*8)+10, centerY-clockRadius*Math.cos(Math.PI/6*8)-3, "8");
      Text t9 = new Text(centerX-clockRadius+12, centerY+4, "9");
      Text t10 = new Text(centerX+clockRadius*Math.sin(Math.PI/6*10)+9, centerY-clockRadius*Math.cos(Math.PI/6*10)+11, "10");
      Text t11 = new Text(centerX+clockRadius*Math.sin(Math.PI/6*11)+4, centerY-clockRadius*Math.cos(Math.PI/6*11)+17, "11");
      Text t12 = new Text(centerX-7, centerY-clockRadius+20, "12");



      //Minute lines
      Line[] array = new Line[60];
      for(int i = 0; i<60; i++){
          if(i%5 == 0){
              array[i] = new Line(centerX+clockRadius*Math.sin(Math.PI/30*i), centerY-clockRadius*Math.cos(Math.PI/30*i),
                              centerX+clockRadius*0.9*Math.sin(Math.PI/30*i), centerY-clockRadius*0.9*Math.cos(Math.PI/30*i));
          }
          else{
              array[i] = new Line(centerX+clockRadius*Math.sin(Math.PI/30*i), centerY-clockRadius*Math.cos(Math.PI/30*i),
                                  centerX+clockRadius*0.95*Math.sin(Math.PI/30*i), centerY-clockRadius*0.95*Math.cos(Math.PI/30*i));
          }
      }    

      // Draw second hand
      double sLength = clockRadius * 0.8;
      double secondX = centerX + sLength * 
        Math.sin(second * (2 * Math.PI / 60));
      double secondY = centerY - sLength * 
        Math.cos(second * (2 * Math.PI / 60));
      Line sLine = new Line(centerX, centerY, secondX, secondY);
      sLine.setStroke(Color.RED);

      // Draw minute hand
      double mLength = clockRadius * 0.65;
      double xMinute = centerX + mLength * 
        Math.sin(minute * (2 * Math.PI / 60));
      double minuteY = centerY - mLength * 
        Math.cos(minute * (2 * Math.PI / 60));
      Line mLine = new Line(centerX, centerY, xMinute, minuteY);
      mLine.setStroke(Color.BLUE);

      // Draw hour hand
      double hLength = clockRadius * 0.5;
      double hourX = centerX + hLength * 
        Math.sin((hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
      double hourY = centerY - hLength *
        Math.cos((hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
      Line hLine = new Line(centerX, centerY, hourX, hourY);
      hLine.setStroke(Color.GREEN);


      getChildren().clear(); // Clear the pane 
      getChildren().addAll(circle, sLine, mLine, hLine);
      getChildren().addAll(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12);
      getChildren().addAll(array);    
    }
  
    @Override
    public void setWidth(double width) {
      super.setWidth(width);
      paintClock();
    }

    @Override
    public void setHeight(double height) {
      super.setHeight(height);
      paintClock();
    }
  }
}
