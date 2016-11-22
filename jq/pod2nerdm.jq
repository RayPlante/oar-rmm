def arrayify: [.];

def nerdm_schema:  "https://www.nist.gov/od/dm/nerdm-schema/v0.1#";

def nerdm_context:  "https://www.nist.gov/od/dm/nerdm-pub-context.jsonld";
    

. as $in |
{
    "@context": nerdm_context,
    "$schema": nerdm_schema,
    "title": .title,
    "contactPoint": .contactPoint,
    "issued": .issued,
    "modified": .modified,
    "description": [ .description ] 
} 


