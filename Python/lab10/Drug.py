from typing import Any


class Drug:
    price = 0

    def __init__(self, name = "no_name", active_substance = "no_active_substance", manufacturer = "no_manufacturer",
                 dose = 0):
        self.name = name
        self.active_substance = active_substance
        self.manufacturer = manufacturer
        self.dose = dose

    def __init__(self, name = "no_name", active_substance = "no_active_substance", manufacturer = "no_manufacturer",
                 dose = 0, price = 0):
        self.name = name
        self.active_substance = active_substance
        self.manufacturer = manufacturer
        self.dose = dose
        self.price = price

    # def __setattr__(self, name: str, value: Any) -> None:
    #     super().__setattr__(name, value)
    #
    # def __getattribute__(self, name: str) -> Any:
    #     return super().__getattribute__(name)

    def to_string(self):
        print('name: %s,  active substance: %s,  manufacturer: %s,  dose: %s,  price: %s  '
              % (self.name, self.active_substance, self.manufacturer, self.dose, self.price) )

    def print_sum(self):
        print("Price: ",  self.price)

    @staticmethod
    def print_static_sum():
        print("Price: ", Drug.price)

if __name__ == '__main__':
    drug1 = Drug()
    drug2 = Drug('Cythramone', 'Cythrodeine', 'Darnytsa', 1, 10)
    drug3 = Drug('Analgine', 'Analganizer', 'Zaporozh-Analgin', 0.5, 15)

    drug1.to_string()
    drug2.to_string()
    drug3.to_string()

    drug1.print_sum()
    Drug.print_static_sum()
    print (Drug.price)
