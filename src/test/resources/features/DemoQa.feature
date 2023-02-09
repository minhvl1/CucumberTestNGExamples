@Feature3
  Feature: Demo QA site

    @RadioButton
    Scenario Outline: Radio <arg0> Button
      Given Go to demoqa url
      When check <arg0> radio button
      Then See result contain <arg0>

      Examples:
        | arg0   |
        | "Yes" |
        |"Impressive"|
