Feature: the item registration endpoint
  Scenario: client makes call to POST item registration
    Given client wants to register a new item to sell
    When client calls item registration endpoint
    Then client receives item registration data