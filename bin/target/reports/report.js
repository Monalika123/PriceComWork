$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("PriceCompare.feature");
formatter.feature({
  "line": 1,
  "name": "iPhone XR (64GB) - Yellow price comparision",
  "description": "I want to comapre iPhone XR (64GB) - Yellow price in Amazon and Flipkart",
  "id": "iphone-xr-(64gb)---yellow-price-comparision",
  "keyword": "Feature"
});
formatter.before({
  "duration": 2190700,
  "status": "passed"
});
formatter.scenario({
  "line": 5,
  "name": "iPhone XR (64GB) - Yellow price comparision",
  "description": "",
  "id": "iphone-xr-(64gb)---yellow-price-comparision;iphone-xr-(64gb)---yellow-price-comparision",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 4,
      "name": "@runTestAmazon"
    }
  ]
});
formatter.step({
  "line": 6,
  "name": "User opens Amazon page",
  "keyword": "Given "
});
formatter.step({
  "line": 7,
  "name": "page is loaded, searches for iPhone model in amazon",
  "rows": [
    {
      "cells": [
        "Model"
      ],
      "line": 8
    },
    {
      "cells": [
        "iPhone 12 Pro Max"
      ],
      "line": 9
    }
  ],
  "keyword": "When "
});
formatter.match({
  "location": "priceComp.user_opens_Amazon()"
});
formatter.result({
  "duration": 6274445500,
  "status": "passed"
});
formatter.match({
  "location": "priceComp.page_is_loaded_search_for_iPhone_XR_64GB_Yellow_in_amazon(DataTable)"
});
formatter.result({
  "duration": 1949957000,
  "status": "passed"
});
formatter.embedding("image/png", "embedded0.png");
formatter.after({
  "duration": 419058100,
  "status": "passed"
});
formatter.after({
  "duration": 1336207700,
  "status": "passed"
});
formatter.before({
  "duration": 91900,
  "status": "passed"
});
formatter.scenario({
  "comments": [
    {
      "line": 10,
      "value": "#\tAnd Gets the price of the selected iPhone"
    },
    {
      "line": 11,
      "value": "#\tThen navigates to flipkart"
    },
    {
      "line": 12,
      "value": "#\tWhen page is loaded, searches for iPhone XR 64GB Yellow in flipkart"
    },
    {
      "line": 13,
      "value": "#\tAnd Gets the price of the selected iPhone"
    },
    {
      "line": 14,
      "value": "#\tAnd find which website has lesser value"
    }
  ],
  "line": 17,
  "name": "iPhone XR (64GB) - Yellow price comparision",
  "description": "",
  "id": "iphone-xr-(64gb)---yellow-price-comparision;iphone-xr-(64gb)---yellow-price-comparision",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 16,
      "name": "@runTestFlipkart"
    }
  ]
});
formatter.step({
  "line": 18,
  "name": "User opens Flipkart page",
  "keyword": "Given "
});
formatter.match({
  "location": "priceComp.user_opens_flipkart()"
});
formatter.result({
  "duration": 6398421200,
  "status": "passed"
});
formatter.embedding("image/png", "embedded1.png");
formatter.after({
  "duration": 393128600,
  "status": "passed"
});
formatter.after({
  "duration": 652967200,
  "status": "passed"
});
});