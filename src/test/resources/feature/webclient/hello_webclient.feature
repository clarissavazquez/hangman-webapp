Feature: Hangman web page
  As a user I want to be greeted by the name of my choice to become amused

  Scenario: Input the pattern abc 
    Given I am on the hangman page
    When I enter "abc"
    Then I see the replacement "ca."

