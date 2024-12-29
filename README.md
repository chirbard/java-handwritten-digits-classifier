# Java Handwritten Digits Classifier

Simple Java program to classify a single handwritten digit from 0 to 9.

Trained model from: https://www.kaggle.com/code/wwsalmon/simple-mnist-nn-from-scratch-numpy-no-tf-keras

## Installation and compiling
### 1. Download source
### 2. Compile
```sh
javac Tuvastamine.java
```

## Usage

### 1. Download digit dataset 
Example handwritten digits dataset: https://www.kaggle.com/competitions/digit-recognizer

Place the data in folder `data/test.csv`
### 2. Run the program
```sh
java Tuvastamine
```

## Example usage
```
$ java Tuvastamine 
Mitmendat pilti soovid testida (1-28000): 
400
                            
                            
                            
                            
                            
                            
           .+%@@@@#:        
      .:+*@@@@@@@@@@-       
      +@@@@@@*-.  :@%.      
      +@@@*.    .*@@@.      
      .::       *%%##.      
              .=@@@*        
            .:#@@@@@#       
            #@@@*- :#*      
            -@+-    :@:     
             .      -@+     
                    -@%     
                   =@@*     
                 .+@@%:     
                .%@@@=      
   #.         :#@@@#:       
   *@+- ..-=#@@@@@=         
   =@@@@@@@@@@@@:           
     +@@@@@@**-.            
                            
                            
                            
                            
Mudeli hinnang: 3

Kas soovid veel testida? (jah/ei)
jah

Mitmendat pilti soovid testida (1-28000): 
-45
Sellise indeksiga pilti pole, proovi uuesti!

Mitmendat pilti soovid testida (1-28000): 
1
                            
                            
                            
                            
               -##.         
          +@@@@@@@.         
         :%@@@@@@@%#.       
         %@@@@@@@@@@%:      
         :@@@@=..=@@@+      
          :===.   %@@@      
                  :@@@      
                  .@@@      
                  -@@@      
                  %@@@      
            :--  .%@@#      
        =-##%@@##%@@%.      
    .=*@@@@@@@@@@@@@%=      
    =@@@@@%%@@@@@@@@@@*     
    @@@@#- .#@@@@==#@@@*    
    @@@@@%%%@@@%:   *%@@    
    *@@@@@@@@%=:     :@*    
     .*****.                
                            
                            
                            
                            
                            
                            
Mudeli hinnang: 2

Kas soovid veel testida? (jah/ei)
ei

$ 
```

![Screenshot from 2024-12-29 23-10-11](https://github.com/user-attachments/assets/e484f76e-4b51-4491-a0d8-371b67191684)
![Screenshot from 2024-12-29 23-13-21](https://github.com/user-attachments/assets/6b789107-cf17-4937-a1b4-9ecd7217218c)


