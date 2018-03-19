package example.com.android.justjava;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class JustJava extends AppCompatActivity {
    int quantity=2;
    boolean isChecked=true;
    boolean chocoltateIsChecked = true;
    String textName="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_just_java);

    }

    /**
     * This method is called when the + button is clicked.
     */
    public void increment (View view)
    {
        if (quantity==100)
        {
            Toast.makeText(this, "You can't have more than 100 cups of coffe",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        quantity= quantity+1;
        displayQuantity(quantity);
    }


    /**
     * This method is called when the - button is clicked.
     */
    public void decrement (View view)
    {
        if (quantity==1)
        {
            Toast.makeText(this, "You can't have less than 1 cup of coffe",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        quantity=quantity-1;
        displayQuantity(quantity);
    }
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        String priceMessage = createOrderSummary();

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for " + textName);
        intent.putExtra(Intent.EXTRA_TEXT,  priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

    /**
     * Calculates the price of the order.
     *
     *  quantity is the number of cups of coffee ordered
     * @param addChocolate whether is chocolate topping
     * @param addWhippedCream  is used when WhippedCream topping is selected
     */
    private int calculatePrice( boolean addWhippedCream, boolean addChocolate) {
        int price = 5;
        if(addWhippedCream)
        {
            price = price +1;
        }
        if (addChocolate ) {

           price = price + 2;
        }

            return (quantity * price);
    }

    /**
     *
     *Create a summery of the order
     * @return the text summary
     */

    private String createOrderSummary()
    {

        return "Name: " + textName+  "\nAdd whipped cream? " + isChecked +  "\nAdd Chocolate? " + chocoltateIsChecked
                + "\nQuantity: " + quantity + "\nTotal: " + calculatePrice(isChecked, chocoltateIsChecked) + "\nThank you!";


    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView =  findViewById(R.id.quantity_text_view);
        
        quantityTextView.setText("" + number);
    }

    /**
     * This method checks if the checkBox is checked
     */
     public void ifCheckedCheckBox (View view)
     {

         //code to check if this checkbox is checked!
         CheckBox checkBox =  findViewById(R.id.toppingCheckBox);
         isChecked = checkBox.isChecked();

         CheckBox chocolateCheckbox =  findViewById(R.id.chocolate_checkbox);
         chocoltateIsChecked= chocolateCheckbox.isChecked();
     }

    /**
     *This method gets the text input from a user
     */

     public void inputName(View view)
     {
        EditText editText = findViewById(R.id.nameField);
        textName = editText.getText().toString();

     }



}