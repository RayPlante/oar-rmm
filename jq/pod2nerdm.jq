# jq conversion script from NIST-PDL-POD to NERDm schemas
#

# the base NERDm JSON schema namespace
#
def nerdm_schema:  "https://www.nist.gov/od/dm/nerdm-schema/v0.1#";

# the NERDm context location
#
def nerdm_context: "https://www.nist.gov/od/dm/nerdm-pub-context.jsonld";

# where the Datacite Document Reference types are defined
#
def dciteRefType: "https://www.nist.gov/od/dm/nerdm-schema/v0.1#/definitions/DCiteDocumentReference";

# the resource identifier provided on the command line
#
def resid:  if $id then $id else null end;

# conversion for a POD-to-NERDm reference node
#
def cvtref:  {
    "@type": "deo:BibliographicReference",
    "refType": "IsReferencedBy",
    "location": .,
    "$extensionSchemas": [ dciteRefType ]
};

# conversion for a POD-to-NERDm distribution node.  A distribution gets converted
# to a DataFile component
#
def dist2download: 
    .["@type"] = [ "nrdp:DataFile", "dcat:Distribution" ] |
    .["$extensionSchemas"] = [ "https://www.nist.gov/od/dm/nerdm-schema/pub/v0.1#/definitions/DataFile" ] 
;

# conversion for a POD-to-NERDm distribution node.  A distribution gets converted
# to a Hidden component that is not intended for external use.  (Such nodes
# exist to preserve information for conversion back into POD.)
#
def dist2hidden:
    .["@type"] = [ "nrd:Hidden", "dcat:Distribution" ] 
;

# conversion for a POD-to-NERDm distribution node.  A distribution gets converted
# to an Inaccessible component.  This is for distributions that have neither an
# accessURL nor a downloadURL.  
#
def dist2inaccess:
    .["@type"] = [ "nrd:Inaccessible", "dcat:Distribution" ]
;

# conversion for a POD-to-NERDm distribution node.  A distribution gets converted
# to a generic AccessPage component.  
#
def dist2accesspage:
    .["@type"] = [ "nrd:AccessPage", "dcat:Distribution" ]
;

# conversion for a POD-to-NERDm distribution node.  A distribution gets converted
# to a component of particular types depending on the input data.  See other
# dist2* macros
#
def dist2comp: 
    if .downloadURL then
        dist2download
    else if .accessURL then
        if (.accessURL | test("doi.org")) then
          dist2hidden
        else
          dist2accesspage
        end
      else
        dist2inaccess
      end
    end
;

# Converts an entire POD Dataset node to a NERDm Resource node
#
def podds2resource: 
    . as $in |
    {
        "@context": nerdm_context,
        "$schema": nerdm_schema,
        "$extensionSchemas": [ ],
        "@type": [ "nrdp:PublishedDataResource" ],
        "@id": resid,
        "doi": null,
        title,
        contactPoint,
        issued,
        modified,

        ediid: .identifier,
        landingPage,
        
        description: [ .description ],
        keyword,

        references,
        accessLevel,
        license,
        components: .distribution,
        publisher,
        language,
        bureauCode,
        programCode
    } |
    if .references then .references = (.references | map(cvtref)) else del(.references) end |
    if .components then .components = (.components | map(dist2comp)) else del(.components) end 
;

# Converts an entire POD Catalog to an array of NERDm Resource nodes
#
def podcat2resources:
    . | .dataset | map(podds2resource)
;



