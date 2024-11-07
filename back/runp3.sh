#!/bin/bash

if [[ ! -f .env ]]; then
  echo ".env file not found. Please create a .env file with the required database environment variables. And rerun this command"
  touch .env &&
  echo "DB_PORT=
DB_NAME=
DB_USERNAME=
DB_PASSWORD=" > .env &&
  case "$OSTYPE" in
    darwin*)  open -e .env ;;                # macOS
    linux*)   xdg-open .env ;;             # Linux
    msys* | cygwin*) start .env ;;         # Windows
    *) echo "Unsupported OS: $OSTYPE" && exit 1 ;;    # Fallback for unsupported OS
  esac
  exit 1
else
  source .env
  export DB_PORT=$DB_PORT
  export DB_NAME=$DB_NAME
  export DB_USERNAME=$DB_USERNAME
  export DB_PASSWORD=$DB_PASSWORD
  export ASSETS_PATH="$PROJECT_DIR$/../shared/assets/"
  export ASSETS_URL="http://localhost:9090/api/files/file/"
  echo $(pwd)
fi

echo "Did you create the database '$DB_NAME'? (Y/n)"
read -r user_input_db_create

if [[ "$user_input_db_create" != "y" && "$user_input_db_create" != "Y" && "$user_input_db_create" != "" ]]; then
  echo "Please create the database '$DB_NAME' and run the script again."
  exit 1
fi

echo "Did you run schema.sql and data.sql script in $(pwd)/src/main/resources/ ? (Y/n)"
read -r user_input_sql_scripts

if [[ "$user_input_sql_scripts" != "y" && "$user_input_sql_scripts" != "Y" && "$user_input_sql_scripts" != "" ]]; then
  echo "Please execute the following sql scripts in your db '$DB_NAME' and run the script again."
  echo "Create schema: $(pwd)/src/main/resources/schema.sql"
  echo "Insert data: $(pwd)/src/main/resources/data.sql"
  exit 1
fi

./gradlew bootRun
