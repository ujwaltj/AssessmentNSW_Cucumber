Feature:Apply Filter for Different Ministers under the Ministers Accordian in NSW website

  As a user of the NSW website
  I want to be able to select minister names in the Ministers accordian
  So that I can apply the filter and verify the media cards pertaining to the corresponding minister is displayed
  Background:
    Given I am on the media release page

Scenario:Successful in applying the filter for a minister and their corresponding media cards
  #Given I am on the media release page
  When I click on the "Treasurer"
  And Apply the filter
  Then Select one of the media card to check if it is pertaining to the corresponding minister

Scenario:Successful in applying the filter for a minister and checking if the Url has contains the ministers name
  When I click on the "The Premier"
  And Apply the filter
  Then I can valid the minister name in the Url "The Premier"

Scenario: Successful in clearing the filter and checking if the Url does not contain the ministers name
  When I click on the "Minister for Aboriginal Affairs and Treaty"
  And Apply the filter
  Then I can valid the minster name is not present in the Url and the Url matches with the original url