# JRegex

**JRegex** is a small regex matcher built from scratch with:  
- a handwritten **lexer**  
- a **recursive-descent parser**  
- a **compiler** to bytecode  
- a lightweight **VM** to execute it  

---

## Supported Syntax
- **Concatenation**: `ab`  
- **Alternation**: `a|b`  
- **Grouping**: `(ab|cd)`  
- **Kleene star**: `a*`  
- **One or more**: `a+`  

---

## Example
Pattern: `a(b|c)*d`  
Input: `abcbcd` → ✅ match  

---

## How it Works
1. **Lexer** → turns pattern into tokens.  
2. **Parser** → builds an AST with recursive descent.  
3. **Compiler** → converts AST into bytecode.  
4. **VM** → runs bytecode against input string.  
