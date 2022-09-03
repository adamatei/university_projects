import re


def validate_email(value):
    regex = r"\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Z|a-z]{2,}\b"

    if re.fullmatch(regex, value) == None:
        return False
    return True


def validate_password(value):
    min_password_length = 6

    if len(value) < min_password_length:
        return False
    return True


def validate_email_password(data):
    return validate_password(data["password"]) and validate_email(data["email"])
