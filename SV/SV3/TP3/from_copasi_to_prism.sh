#!/bin/sh
[ -z "$1" ] || [ -z "$2" ] && {
  echo
  echo Usage:
  echo "        $0   SBML_input_file   PRISM_output_file"
  echo
  exit 0
}

PRISMDIR="/home/m2mocad/allarte/bin/prism-4.3-linux64"

cat "$1" | sed 's/Concentration/Amount/g' > "$2".tmp.xml
[ -f "$2" ] && mv "$2" "$2".bak
java -cp "$PRISMDIR"/lib/prism.jar prism.SBML2Prism "$2".tmp.xml > "$2"
rm "$2".tmp.xml

