#sudoku

# Demo

## Code
```java
        val elements = SudokuUtils.string2Sudoku(
                        "700005010" +
                        "000070006" +
                        "008000430" +
                        "000000064" +
                        "002839500" +
                        "510000000" +
                        "064000100" +
                        "300080000" +
                        "020400009")
        print("new Sudoku:")
        SudokuUtils.print(elements)
        SudokuUtils.enableLog(true)
        val sudoku = Sudoku(elements)
        sudoku.start()
        val result = sudoku.result()
        print("\nresult:")
        SudokuUtils.print(result)
```
## Output

```text
new Sudoku:

	700	005	010
	000	070	006
	008	000	430

	000	000	064
	002	839	500
	510	000	000

	064	000	100
	300	080	000
	020	400	009
BlockRule sure:[num=8,x=1,y=5]
optionsFlags:[-2, -2, -2, -2, -1, -2, -1, 21, -2]
RowRule sure:[num=7,x=2,y=8]
optionsFlags:[-2, -2, -1, -1, -2, -2, 40, -1, -2]
ColumnRule sure:[num=8,x=3,y=1]
optionsFlags:[-1, -1, -2, -1, -2, -1, -2, 49, -2]
RowRule sure:[num=4,x=7,y=7]
optionsFlags:[-2, -2, -1, 119, -2, -2, -2, -1, -2]
ColumnRule sure:[num=4,x=5,y=5]
optionsFlags:[-2, -2, -2, 85, -1, -2, -2, -1, -1]
RowRule sure:[num=4,x=0,y=4]
optionsFlags:[-1, -2, -2, 4, -1, -2, -1, -2, -2]
RowRule sure:[num=4,x=1,y=0]
optionsFlags:[-2, -2, -2, 16, -2, -1, -1, -1, -2]
BlockRule sure:[num=2,x=2,y=0]
optionsFlags:[-2, 32, -2, 16, -2, 2, -1, -1, -2]
BlockRule sure:[num=4,x=1,y=0]
optionsFlags:[-2, 32, -2, 16, -2, 2, -1, -1, -2]
RowRule sure:[num=2,x=2,y=0]
optionsFlags:[-2, 32, -1, -1, 33, -2, -1, -1, -2]
BlockRule sure:[num=6,x=0,y=2]
optionsFlags:[-2, 32, -2, 16, -2, 2, -1, -1, -2]
RowRule sure:[num=5,x=2,y=1]
optionsFlags:[-2, 32, -1, -1, 33, -2, -1, -1, -2]
ColumnRule sure:[num=1,x=8,y=0]
optionsFlags:[128, 32, -1, -1, -1, -1, -1, -2, -2]
ColumnRule sure:[num=2,x=2,y=0]
optionsFlags:[128, 32, -1, -1, -1, -1, -1, -2, -2]
ColumnRule sure:[num=7,x=7,y=1]
optionsFlags:[-1, -1, -2, -1, -1, -1, 113, -1, -2]
ColumnRule sure:[num=1,x=1,y=2]
optionsFlags:[18, -1, -2, -1, -2, -1, -2, -1, -2]
BlockRule sure:[num=5,x=1,y=7]
optionsFlags:[-1, -2, -1, -1, 23, -1, -1, -2, -2]
BlockRule sure:[num=9,x=7,y=2]
optionsFlags:[-1, -1, -1, -1, -2, -1, -1, -1, 114]
BlockRule sure:[num=7,x=8,y=6]
optionsFlags:[-1, -2, -2, -1, -2, -2, 134, -2, -1]
ColumnRule sure:[num=7,x=8,y=6]
optionsFlags:[-1, -2, -2, -1, -1, -2, 134, -2, -2]
ColumnRule sure:[num=9,x=5,y=7]
optionsFlags:[-1, -2, -1, -1, -1, -1, -1, -2, 87]
RowRule sure:[num=3,x=8,y=5]
optionsFlags:[-1, -1, 133, -1, -1, -2, -1, 135, -1]
RowRule sure:[num=8,x=8,y=7]
optionsFlags:[-1, -1, 133, -1, -1, -2, -1, 135, -1]
BlockRule sure:[num=3,x=8,y=5]
optionsFlags:[-2, -2, 133, -1, -2, -2, -2, -1, -2]
RowRule sure:[num=3,x=6,y=8]
optionsFlags:[-1, -2, 104, -1, -2, -1, -2, -1, -2]
ColumnRule sure:[num=6,x=7,y=6]
optionsFlags:[-1, -2, -2, -1, -1, 118, -1, -2, -2]
ColumnRule sure:[num=5,x=7,y=8]
optionsFlags:[-1, -2, -1, -1, 120, -1, -1, -2, -1]
RowRule sure:[num=6,x=8,y=4]
optionsFlags:[-1, -1, -1, -1, -1, 132, -1, -1, -1]
BlockRule sure:[num=6,x=8,y=4]
optionsFlags:[-2, -2, -1, -1, -2, 132, -2, -1, -2]
ColumnRule sure:[num=6,x=2,y=5]
optionsFlags:[-2, -2, -1, -1, -1, 37, -2, -1, -1]
RowRule sure:[num=6,x=2,y=5]
optionsFlags:[-2, -1, -1, -1, -1, 37, -1, -1, -2]
ColumnRule sure:[num=6,x=5,y=3]
optionsFlags:[-2, -2, -2, -1, -2, 83, -2, -1, -2]
BlockRule sure:[num=6,x=2,y=5]
optionsFlags:[-2, -2, -2, -1, -1, 37, -1, -1, -2]
RowRule sure:[num=7,x=5,y=2]
optionsFlags:[-1, -2, -2, -1, -1, -1, 82, -2, -1]
BlockRule sure:[num=3,x=3,y=2]
optionsFlags:[-1, -1, 50, -1, -1, -1, -1, -1, -1]
BlockRule sure:[num=7,x=3,y=3]
optionsFlags:[-2, -1, -1, -1, -2, -1, 51, -1, -1]
RowRule sure:[num=2,x=3,y=6]
optionsFlags:[-2, 54, -1, -1, 52, -1, -1, -1, -1]
RowRule sure:[num=5,x=3,y=4]
optionsFlags:[-2, 54, -1, -1, 52, -1, -1, -1, -1]
RowRule sure:[num=3,x=5,y=6]
optionsFlags:[-1, -1, 86, -1, -1, -1, -1, -2, -1]
RowRule sure:[num=1,x=3,y=5]
optionsFlags:[53, -1, -1, -1, -1, -1, -1, -1, -1]
ColumnRule sure:[num=2,x=0,y=8]
optionsFlags:[-1, 8, -1, -1, -1, -1, -1, -2, -1]
BlockRule sure:[num=2,x=1,y=3]
optionsFlags:[-2, 19, -2, -1, -1, -1, -1, -1, -2]
ColumnRule sure:[num=2,x=7,y=5]
optionsFlags:[-1, 117, -1, -1, -1, -1, -1, -1, -1]
RowRule sure:[num=5,x=6,y=3]
optionsFlags:[-1, -1, -1, -1, 99, -1, -1, -1, -2]
ColumnRule sure:[num=8,x=0,y=6]
optionsFlags:[-1, -1, -1, -1, -1, -1, -1, 6, -2]
BlockRule sure:[num=8,x=5,y=8]
optionsFlags:[-1, -1, -1, -1, -1, -1, -1, 88, -1]
BlockRule sure:[num=1,x=7,y=3]
optionsFlags:[115, -1, -1, -1, -1, -1, -1, -1, 100]
BlockRule sure:[num=9,x=6,y=4]
optionsFlags:[115, -1, -1, -1, -1, -1, -1, -1, 100]
ColumnRule sure:[num=8,x=5,y=8]
optionsFlags:[-1, -1, -1, -1, -1, -1, -1, 88, -1]
ColumnRule sure:[num=9,x=0,y=1]
optionsFlags:[-1, -1, -2, -1, -1, -1, -1, -1, 1]
ColumnRule sure:[num=3,x=0,y=3]
optionsFlags:[-1, -1, 3, -1, -1, -1, -1, -1, 35]
ColumnRule sure:[num=9,x=2,y=3]
optionsFlags:[-1, -1, 3, -1, -1, -1, -1, -1, 35]
result:

	796	345	812
	431	278	956
	258	916	437

	983	751	264
	642	839	571
	517	624	398

	864	597	123
	379	182	645
	125	463	789
```