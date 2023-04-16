roman_numerals = {
    'I': 1, 'V': 5, 'X': 10, 'L': 50, 'C': 100, 'D': 500, 'M': 1000,
    'IV': 4, 'IX': 9, 'XL': 40, 'XC': 90, 'CD': 400, 'CM': 900
}

arabic_numerals = {
    1: 'I', 2: 'II', 3: 'III', 4: 'IV', 5: 'V', 6: 'VI', 7: 'VII', 8: 'VIII', 9: 'IX',
    10: 'X', 20: 'XX', 30: 'XXX', 40: 'XL', 50: 'L', 60: 'LX', 70: 'LXX', 80: 'LXXX', 90: 'XC',
    100: 'C', 200: 'CC', 300: 'CCC', 400: 'CD', 500: 'D', 600: 'DC', 700: 'DCC', 800: 'DCCC', 900: 'CM',
    1000: 'M', 2000: 'MM', 3000: 'MMM', 4000: 'MMMCMC'
}

# 로마 숫자를 아라비아 숫자로 변경하는 함수
def romanToArabic(n):
    rlt = 0
    i = 0

    while i < len(n):
        # 두 문자가 합쳐저 숫자가 되는 경우
        if i < len(n) - 1 and roman_numerals.get(n[i] + n[i + 1]):
            rlt += roman_numerals[n[i] + n[i + 1]]
            i += 1
        
        # 한 문자만 숫자가 되는 경우
        else:
            rlt += roman_numerals[n[i]]
        
        i += 1

    return rlt

# 아라비아 숫자를 로마 숫자로 변경하는 함수
def arabicToRoman(n):
    rlt = ''
    devide = 10 ** (len(str(n)) - 1)

    while n > 0:
        tmp = (n // devide) * devide

        # 각 자리 수가 0인 경우 키에러 방지
        if arabic_numerals.get(tmp):
            rlt += arabic_numerals[tmp]
        
        n %= devide
        devide //= 10

    return rlt

# 로마 숫자 입력받고 아라비아 숫자로 변환
first_num = input()
second_num = input()

first_num = romanToArabic(first_num)
second_num = romanToArabic(second_num)

# 두 수를 더한 수를 출력하고 로마 숫자로 변환후 다시 출력
new_num = first_num + second_num

print(new_num)

new_num = arabicToRoman(new_num)

print(new_num)