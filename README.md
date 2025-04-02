# Calculator

![Untitled design (9)](https://github.com/user-attachments/assets/4feadd11-d164-4a86-b3a8-fb1132c4ef36)


## Overview
This is a simple yet functional calculator application built using JavaFX for **CS216 at Gettysburg College**. The application features a user-friendly interface, styled buttons, and a robust expression evaluation mechanism using stacks for operand and operator management.

## Features
- **Basic Arithmetic Operations**: Addition, Subtraction, Multiplication, and Division.
- **Exponentiation**: Includes a power function (`xʸ`).
- **Parentheses Support**: Allows grouping of expressions.
- **Clear Function**: Resets the screen and internal stacks.
- **Custom Styling**: Dark-themed UI with interactive button effects.

## Installation and Running
### Prerequisites
- Java Development Kit (JDK) 8 or higher
- JavaFX SDK (if using JDK 8)

### Steps
1. Clone or download this repository.
2. Compile and run the application using:
   ```sh
   javac *.java
   java Calculator
   ```

## How It Works
- The calculator maintains two stacks:
  - **Operand Stack**: Stores numerical values.
  - **Operator Stack**: Stores operators with their precedence.
- When an operator is encountered, the application evaluates expressions based on precedence and updates the stacks accordingly.
- The display updates dynamically as buttons are pressed.

## UI Components
- **Screen**: Displays user input and results.
- **Keypad**: Grid of buttons for numbers, operations, and control actions.
- **Styled Buttons**: Different colors for numeric keys, operators, and special functions.

## Future Enhancements
- Implement support for more advanced functions (e.g., square root, logarithm).
- Improve error handling for invalid expressions.
- Add keyboard input support.

## Author
Marko Tsymbaliuk

## License
This project is for educational purposes as part of **CS216 at Gettysburg College**.

