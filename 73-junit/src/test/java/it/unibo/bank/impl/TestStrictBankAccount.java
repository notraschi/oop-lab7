package it.unibo.bank.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.bank.api.AccountHolder;
import it.unibo.bank.api.BankAccount;

/**
 * Test class for the {@link StrictBankAccount} class.
 */
class TestStrictBankAccount {

    private static final double AMOUNT = 100.0; 
    private static final double EXPECTED_TRANSACTION_FEE = 0.1;
    private static final double EXPECTED_MANAGEMENT_FEE  = 5.0;

    // Create a new AccountHolder and a StrictBankAccount for it each time tests are executed.
    private AccountHolder mRossi;
    private BankAccount bankAccount;

    /**
     * Prepare the tests.
     */
    @BeforeEach
    public void setUp() {
        this.mRossi = new AccountHolder("Matteo", "Rossi", 1);
        this.bankAccount = new StrictBankAccount(mRossi, 0.0);
    }

    /**
     * Test the initial state of the StrictBankAccount.
     */
    @Test
    public void testInitialization() {
       assertEquals(0.0, bankAccount.getBalance()); 
       assertEquals(0, bankAccount.getTransactionsCount());
       assertEquals(mRossi, bankAccount.getAccountHolder());
    }

    /**
     * Perform a deposit of 100€, compute the management fees, and check that the balance is correctly reduced.
     */
    @Test
    public void testManagementFees() {
        bankAccount.deposit(mRossi.getUserID(), AMOUNT);
        bankAccount.chargeManagementFees(mRossi.getUserID());
        assertEquals(
            AMOUNT - EXPECTED_MANAGEMENT_FEE - EXPECTED_TRANSACTION_FEE,
            bankAccount.getBalance()
        );
    }

    /**
     * Test that withdrawing a negative amount causes a failure.
     */
    @Test
    public void testNegativeWithdraw() {
        try {
            bankAccount.withdraw(mRossi.getUserID(), -1);
            fail("withdrawing a negative amout of money shouldn't have been possible");
        } catch (final IllegalArgumentException e) {
            assertEquals(0, bankAccount.getBalance());
            assertEquals(0, bankAccount.getTransactionsCount());
        }
    }

    /**
     * Test that withdrawing more money than it is in the account is not allowed.
     */
    @Test
    public void testWithdrawingTooMuch() {
        bankAccount.deposit(mRossi.getUserID(), AMOUNT);
        try {
            bankAccount.withdraw(mRossi.getUserID(), AMOUNT);
            fail("withdrawing more money than the balance shouln't have been possible");
        } catch (final IllegalArgumentException e) {
            assertEquals(AMOUNT, bankAccount.getBalance());
            assertEquals(1, bankAccount.getTransactionsCount());
        }
    }
}
