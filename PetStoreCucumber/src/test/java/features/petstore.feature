Feature: Validating PetStore APIs

@AddPet
Scenario: To verify adding a pet
    Given create pet endpoint
    When I send a POST request to endpoint
    Then the response statusCode should be 200
    And the pet should be created successfully with passed details
@UpdatePet
Scenario:  To verify updating an existing pet details
    Given update pet details endpoint
    When I send a PUT request to endpoint
    Then the response statusCode should be 200
    And the pet details should get updated successfully with passed details   
@GetPet
Scenario: To verify retriving the pet details by id
    Given fetching the pet details endpoint
    When I get the pet details by id
    Then the response statusCode should be 200
    And the pet details should be retrieved successfully
