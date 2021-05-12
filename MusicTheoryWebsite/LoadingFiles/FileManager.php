<?php

require_once('AboutUsData.php');
require_once('../DATABASE/connection.php');
require_once('Section.php');
require_once('Material.php');
require_once('AdditionalMaterialsData.php');


class FileManager
{
	private $data;
    private $theory;
	private $sections = array();
    private $guitar = array();
    private $piano = array();
    private $drums = array();
    private $flute = array();
 
    function __construct()
    {
       $this->data = new AboutUsData();
       $this->theory = new AdditionalMaterialsData();
    }

    public function AddSections()
    {
        $total = $this->data->CountSections();

    	for ($x = 1; $x <= $total; $x++) 
    	{
             if($this->data->getSectionById($x) == null)
             {
             	$x++;
             }
             else
             {
             	$tempSection = $this->data->getSectionById($x);

             	$section = new Section($tempSection['title'], $tempSection['text']);

             	$this->sections[] = $section;
             }
        }
    }

    public function GetSections()
    {
    	return $this->sections;
    }


    public function AddGuitarMaterials($instrumentid)
    {
        $total = $this->theory->CountMaterials();

        for ($x = 1; $x <= $total; $x++) 
        {
            
                $tempMaterial = $this->theory->getMaterialById($x);

                if($tempMaterial['instrumentid'] == $instrumentid)
            {

                $material = new Material($tempMaterial['text'], $tempMaterial['image']);

                $this->guitar[] = $material;
            }
            else
            {
                $x++;
             
            }

        }
    }



    public function GetGuitarMaterials()
    {
        return $this->guitar;
    }



    public function AddPianoMaterials($instrumentid)
    {
        $total = $this->theory->CountMaterials();

        for ($x = 1; $x <= $total; $x++) 
        {
            
                $tempMaterial = $this->theory->getMaterialById($x);

                if($tempMaterial['instrumentid'] == $instrumentid)
            {

                $material = new Material($tempMaterial['text'], $tempMaterial['image']);

                $this->piano[] = $material;
            }
            else
            {
                $x++;
             
            }

        }
    }


     public function GetPianoMaterials()
    {
        return $this->piano;
    }




    public function AddDrumsMaterials($instrumentid)
    {
        $total = $this->theory->CountMaterials();

        for ($x = 1; $x <= $total; $x++) 
        {
            
                $tempMaterial = $this->theory->getMaterialById($x);

                if($tempMaterial['instrumentid'] == $instrumentid)
            {

                $material = new Material($tempMaterial['text'], $tempMaterial['image']);

                $this->drums[] = $material;
            }
            else
            {
                $x++;
             
            }

        }
    }


     public function GetDrumsMaterials()
    {
        return $this->drums;
    }





    public function AddFluteMaterials($instrumentid)
    {
        $total = $this->theory->CountMaterials();

        for ($x = 1; $x <= $total; $x++) 
        {
            
                $tempMaterial = $this->theory->getMaterialById($x);

                if($tempMaterial['instrumentid'] == $instrumentid)
            {

                $material = new Material($tempMaterial['text'], $tempMaterial['image']);

                $this->flute[] = $material;
            }
            else
            {
                $x++;
             
            }

        }
    }


     public function GetFluteMaterials()
    {
        return $this->flute;
    }




}
?>