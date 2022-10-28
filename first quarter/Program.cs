string[] array = new string[]{"a", "Boba", "Goga", "Git", "Ginza", "good", "1"};
int maxLength = 3;

int arrayStartLength = array.Length;
int arrayResultSize = 0;
string[] arrayResult = new string[arrayResultSize];

for (int i = 0; i < array.Length; i++)
{
    if (array[i].Length <= maxLength) 
    {
        string[] arrayTemp = new string[arrayResultSize + 1];
        arrayResult.CopyTo(arrayTemp, 0);
        arrayResultSize = arrayTemp.Length;
        arrayTemp[arrayResultSize - 1] = array[i];
        arrayResult = new string[arrayResultSize];
        arrayTemp.CopyTo(arrayResult, 0);
        arrayResultSize++;
    }
}

Console.WriteLine();
Console.WriteLine("Array after fill:");
PrintArray(array);
Console.WriteLine();
Console.WriteLine();
Console.WriteLine("New array:");
PrintArray(arrayResult);
Console.WriteLine();
Console.WriteLine();




void PrintArray(string[] array)
{
    for (int i = 0; i < array.Length; i++)
    {
        Console.Write($"{array[i]} ");
    }
}