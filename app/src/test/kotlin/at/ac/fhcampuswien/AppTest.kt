/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package at.ac.fhcampuswien

import kotlin.test.*

class AppTest {

    @Test fun appHasGenerateRandomNonRepeatingNumber() {
        val classUnderTest = App()
        assertNotNull(classUnderTest.generateRandomNonRepeatingNumber, "app should have a lambda expression called generateRandomNonRepeatingNumber")
    }

    @Test fun appHasCheckUserInputAgainstGeneratedNumber() {
        val classUnderTest = App()
        assertNotNull(classUnderTest.checkUserInputAgainstGeneratedNumber, "app should have a lambda expression called checkUserInputAgainstGeneratedNumber")
    }

    @Test
    fun generateRandomNonRepeatingNumberCorrectNumberOfDigits() {
        val classUnderTest = App()
        val generatedNumber = classUnderTest.generateRandomNonRepeatingNumber(4)

        assertEquals(4, generatedNumber.toString().length)
    }

    @Test
    fun generateRandomNonRepeatingNumberNonRepeatingDigits() {
        val classUnderTest = App()
        val generatedNumber = classUnderTest.generateRandomNonRepeatingNumber(9)

        val digitSet = mutableSetOf<Char>()
        generatedNumber.toString().forEach {
            assertTrue(digitSet.add(it))
        }
    }


    @Test
    fun generateRandomNonRepeatingNumberTooBig() {
        val classUnderTest = App()
        val numberOfDigits = 10

        assertFailsWith<IllegalArgumentException> {
            classUnderTest.generateRandomNonRepeatingNumber(numberOfDigits)
        }
    }

    @Test
    fun generateRandomNonRepeatingNumberActuallyRandom() {
        val classUnderTest = App()
        val numberOfDigits = 9

        // generate a set to save the values generated by this test
        val randomSet: MutableSet<Int> = mutableSetOf()
        val iteration = 100

        for (i in 1.. iteration) {
            val generatedNumber = classUnderTest.generateRandomNonRepeatingNumber(numberOfDigits)

            // Check if the digits are non-repeating
            val digitSet = mutableSetOf<Char>()
            generatedNumber.toString().forEach {
                assertTrue(digitSet.add(it)) // If the set already contains the digit, the test will fail
            }
            randomSet.add(generatedNumber)
        }

        assertEquals( iteration, randomSet.size) //if not the same size not all number random
    }


    @Test
    fun inputAndRandomNumberHaveNoMatchingDigits() {
        val classUnderTest = App()
        val toTest =  classUnderTest.checkUserInputAgainstGeneratedNumber(1234,8576)
        assertEquals(0, toTest.n)
        assertEquals(0, toTest.m)
    }

    @Test
    fun inputAndRandomNumberHaveAllMatchingDigits1SamePosition() {
        val classUnderTest = App()
        val toTest =  classUnderTest.checkUserInputAgainstGeneratedNumber(5678, 8576)
        assertEquals(4, toTest.n)
        assertEquals(1, toTest.m)
    }

    @Test
    fun inputAndRandomNumberHave1MatchingDigit1SamePosition() {
        val classUnderTest = App()
        val toTest =  classUnderTest.checkUserInputAgainstGeneratedNumber(5555,8576)
        assertEquals(1, toTest.m)
        assertEquals(1, toTest.n)
    }

    @Test
    fun inputAndRandomNumberHave3MatchingDigits2SamePosition() {
        val classUnderTest = App()
        val toTest =  classUnderTest.checkUserInputAgainstGeneratedNumber(3586, 8576)
        assertEquals(3, toTest.n)
        assertEquals(2, toTest.m)
    }


    @Test
    fun inputAndRandomNumberHaveAllMatchingDigitsAllSamePosition() {
        val classUnderTest = App()
        val toTest =  classUnderTest.checkUserInputAgainstGeneratedNumber(8576, 8576)
        assertEquals(4, toTest.n)
        assertEquals(4, toTest.m)
    }

    @Test
    fun inputAndRandomNumberHaveSomeMatchingDigitsAllSamePosition() {
        val classUnderTest = App()
        val toTest =  classUnderTest.checkUserInputAgainstGeneratedNumber(8576231, 8576429)
        assertEquals(5, toTest.n)
        assertEquals(4, toTest.m)
    }

    @Test
    fun inputAndRandomNumberHave9Digits() {
        val classUnderTest = App()
        val toTest =  classUnderTest.checkUserInputAgainstGeneratedNumber(123456789, 987654321)
        assertEquals(9, toTest.n)
        assertEquals(1, toTest.m)
    }


    @Test
    fun inputAndRandomNumberAreNotValid() {
        val classUnderTest = App()
        assertFailsWith<IllegalArgumentException> {
            classUnderTest.checkUserInputAgainstGeneratedNumber(10, 234)
        }
        assertFailsWith<IllegalArgumentException> {
            classUnderTest.checkUserInputAgainstGeneratedNumber(1434322320, 342432432)
        }
    }

}