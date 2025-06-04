Feature: LabCorp Careers Job Search Automation

  Scenario: Browse and validate LabCorp job listing
    Given I open the LabCorp homepage
    When I navigate to the Careers page
    And I search for "Senior Automation and Robotics Software Engineer"
    And I select the job listing titled "Senior Automation and Robotics Software Engineer"
    Then I verify job details:
      | Job Title           | Senior Automation and Robotics Software Engineer |
      | Job Location        | Location Bloomfield, Connecticut, United States of America |
      | Job ID              | Job ID : 2512136                       |
      | Description         | Design/develop/support application software for automation equipment, including liquid-handling and instrument-tending laboratory robots. |
      | Management Bullet   | 3 – 10 years experience developing application-level software for laboratory and/or industrial automation. |
      | Requirement         | High School Diploma or equivalent. |
      | Additional Knowledge     | Experience with OpenJDK 11 or later a plus                     |
    When I click Apply Now
    Then I verify the Apply Now page job details match
    And I return to the Job Search page
