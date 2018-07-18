package com.shannontheoret.machikorogame;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;

public class MainActivity extends AppCompatActivity {

    private TextView coinCounter;
    private TableLayout playerHandTable;
    private Game game;
    private Spinner cardStockSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cardStockSpinner = (Spinner) findViewById(R.id.card_stock_spinner);
        coinCounter = (TextView) findViewById(R.id.coinCount);
        playerHandTable = (TableLayout) findViewById(R.id.playerHandTable);
        game = new Game();
        populatePlayerHandTable();
        refreshCoinCount();
        populateCardStockSpinner();
    }

    public void rollDice(View v) {
        try {
            Integer rollValue = GameUtils.generateRollValue();
            game.handleRoll(rollValue);
            int diceReplacementImageID = getResources().getIdentifier("dice_" + rollValue, "drawable", "com.shannontheoret.machikorogame");
            ((ImageView) v).setImageResource(diceReplacementImageID);
            refreshCoinCount();
        } catch (Exception e) {
            exceptionToast(e.getMessage());
        }
    }

    public void populatePlayerHandTable(){
        for(Entry<CardType, Integer> cardAndQty : game.player1.hand.cards.entrySet()) {
            CardType cardType = cardAndQty.getKey();
            int cardId = getResources().getIdentifier(cardType.getLabel() + "_qty", "id", getPackageName());
            TextView cardCount = findViewById(cardId);
            cardCount.setText(String.valueOf(cardAndQty.getValue()));
        }
    }

    public void refreshCoinCount(){
        Integer coins = game.player1.coins;
        coinCounter.setText(String.valueOf(coins));
    }

    public void populateCardStockSpinner(){

        List<String> cardStockList = CardStock.getCardStockList();

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this,R.layout.card_stock,cardStockList);

        spinnerArrayAdapter.setDropDownViewResource(R.layout.card_stock);
        cardStockSpinner.setAdapter(spinnerArrayAdapter);
    }

    public void purchaseCard(View v) throws Exception {
        try {
            String cardStockListItem = (String) cardStockSpinner.getSelectedItem();
            game.handlePurchase(cardStockListItem);
            populatePlayerHandTable();
            refreshCoinCount();
            populateCardStockSpinner();
        } catch (Exception e) {
            exceptionToast(e.getMessage());
        }
    }

    public void endTurn(View v) throws Exception {
        try {
            game.handleEndTurn();
        } catch (Exception e) {
            exceptionToast(e.getMessage());
        }
    }

    public void exceptionToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

}
