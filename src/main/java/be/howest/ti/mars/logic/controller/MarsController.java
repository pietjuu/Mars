package be.howest.ti.mars.logic.controller;

import be.howest.ti.mars.logic.domain.Quote;

public interface MarsController {
    Quote getQuote(int quoteId);

    Quote createQuote(String quote);

    Quote updateQuote(int quoteId, String quote);

    void deleteQuote(int quoteId);
}
