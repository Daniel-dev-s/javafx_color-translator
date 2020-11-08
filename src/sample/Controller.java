package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Controller {
    @FXML
    public TextField hexColorInput;
    @FXML
    public Circle colorCircle;
    @FXML
    public TextField rColorInput;
    @FXML
    public TextField gColorInput;
    @FXML
    public TextField bColorInput;
    @FXML
    public TextField hColorInput;
    @FXML
    public TextField sColorInput;
    @FXML
    public TextField lColorInput;
    @FXML
    public Button clear;

    @FXML
    private Button btn;

    @FXML
    public void onClick(){
        try {
        String colorToSet = getInputColor();
        System.out.println(colorToSet);
        if(colorToSet!=null) {
            colorCircle.setFill(Paint.valueOf(colorToSet));
            hexColorInput.setText(colorToSet);

            Color c = (Color)colorCircle.getFill();
            rColorInput.setText(Math.round(c.getRed()*255)+"");
            gColorInput.setText(Math.round(c.getGreen()*255)+"");
            bColorInput.setText(Math.round(c.getBlue()*255)+"");

            String[] hsv = rgbToHsb(c.getRed(),
                    c.getGreen(),
                    c.getBlue());
            hColorInput.setText(hsv[0]);
            sColorInput.setText(hsv[1]);
            lColorInput.setText(hsv[2]);
        }
        }catch (Exception ex){
            System.out.println("eh, error"+ex);
        }
    }
    @FXML
    public void clearFields(){
        hexColorInput.setText("");
        rColorInput.setText("");
        gColorInput.setText("");
        bColorInput.setText("");
        hColorInput.setText("");
        sColorInput.setText("");
        lColorInput.setText("");
    }
    private String[] rgbToHsb(double r,double g,double b){
        double max = Math.max(Math.max(r,g),b);
        double min = Math.min(Math.min(r,g),b);
        double h=0,s=0,v=0;
        if(max==r){
            h=60*((g-b)/(max-min));
            if(g<b)h+=360;
        }
        else if(max==g)h=60*((b-r)/(max-min))+120;
        else h=60*((r-g)/(max-min))+240;
        s = (max==0) ? 0:1-(min/max);
        v = max;
        return new String[]{h+"",s+"",v+""};
    }
    private String getInputColor(){

            if (hexColorInput.getText().length()>1) return hexColorInput.getText();
            if (!rColorInput.getText().equals("") &&
                    !gColorInput.getText().equals("") &&
                    !bColorInput.getText().equals("")) {

                Color c = new Color(Float.parseFloat(rColorInput.getText()) / 255,
                        Float.parseFloat(gColorInput.getText()) / 255,
                        Float.parseFloat(bColorInput.getText()) / 255, 1);
                return c.toString().substring(2, c.toString().length() - 2);
            }
            if(!hColorInput.getText().equals("") &&
               !sColorInput.getText().equals("") &&
               !lColorInput.getText().equals("")){
                Color c = Color.hsb(Float.parseFloat(hColorInput.getText()),
                        Float.parseFloat(sColorInput.getText()),
                        Float.parseFloat(lColorInput.getText()));
                return c.toString().substring(2, c.toString().length() - 2);
            }
        return null;
    }
}
