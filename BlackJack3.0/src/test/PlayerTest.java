package test;

import com.company.Card;
import com.company.ICard;
import com.company.Player;
import org.junit.After;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.is;

class PlayerTest {
    private static Player player;
    private static final InputStream DEFAULT_STDIN = System.in;

    @After
    public void rollbackChangesToStdin() {
        System.setIn(DEFAULT_STDIN);
    }

    @BeforeAll
    static void generatePlayer(){
        player = new Player();
    }

    @Test
    void addToBank() {
        int money = 100;
        player.addToBank(money);
        Assertions.assertEquals(money, player.getBank());

        player.addToBank(money);
        Assertions.assertEquals(money*2, player.getBank());

        int negativeMoney = -100;
        player.addToBank(negativeMoney);
        player.addToBank(negativeMoney);
        player.addToBank(negativeMoney);
        Assertions.assertEquals(negativeMoney, player.getBank());
    }

    @Test
    void addCard() {
        List<ICard> list = new ArrayList<>();
        list.add(new Card(Card.Rank.ACE, Card.Suit.CLUBS));
        list.add(new Card(Card.Rank.JACK, Card.Suit.CLUBS));
        list.add(new Card(Card.Rank.QUEEN, Card.Suit.CLUBS));
        list.add(new Card(Card.Rank.KING, Card.Suit.CLUBS));
        list.add(new Card(Card.Rank.ACE, Card.Suit.SPADES));

        for(ICard card : list){
            player.addCard(card);
        }
        Assert.assertThat(list, is(player.getHand()));
        player.changeActiveHand();
        Assert.assertThat(new ArrayList<>(), is(player.getHand()));

        for(ICard card : list){
            player.addCard(card);
        }
        Assert.assertThat(list, is(player.getHand()));
    }

    @Test
    void splitHand() {
        Assert.assertFalse(player.splitHand());

        player.addCard(new Card(Card.Rank.ACE, Card.Suit.CLUBS));
        player.addCard(new Card(Card.Rank.ACE, Card.Suit.CLUBS));

        Assert.assertTrue(player.splitHand());
    }

    @Test
    void bet() {

        System.setIn(new ByteArrayInputStream("70".getBytes()));

        player = new Player();
        player.addToBank(100);

        player.bet();

        Assert.assertEquals(30, player.getBank());
    }

    @Test
    public void testBarCorrect() {
        System.setIn(new ByteArrayInputStream("7\n2\n3".getBytes()));
        Scanner scanner = new Scanner(System.in);
        System.out.println(scanner.nextInt());
    }


    @Test
    void getValueOfHand() {
        Assert.assertEquals(0, player.getValueOfHand());

        player.addCard(new Card(Card.Rank.ACE, Card.Suit.CLUBS));
        player.addCard(new Card(Card.Rank.ACE, Card.Suit.CLUBS));

        Assert.assertEquals(12, player.getValueOfHand());

        player = new Player();

        player.addCard(new Card(Card.Rank.ACE, Card.Suit.CLUBS));
        player.addCard(new Card(Card.Rank.JACK, Card.Suit.CLUBS));

        Assert.assertEquals(21, player.getValueOfHand());
    }

}