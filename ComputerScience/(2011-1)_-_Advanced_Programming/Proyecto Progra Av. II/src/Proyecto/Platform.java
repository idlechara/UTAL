package Proyecto;

/**
 * A plattfom enum class, made to mark the corresponding platform of a game.
 * @author   Erik Andres Regla Torres
 * @version   	0.1 
 * @since   0.1
 */
public enum Platform {
	//Sony
	/**
	 * @uml.property  name="pS2"
	 * @uml.associationEnd  
	 */
	PS2, /**
	 * @uml.property  name="pSX"
	 * @uml.associationEnd  
	 */
	PSX, /**
	 * @uml.property  name="pSP"
	 * @uml.associationEnd  
	 */
	PSP,
	//Sega
	/**
	 * @uml.property  name="gENESIS"
	 * @uml.associationEnd  
	 */
	GENESIS, /**
	 * @uml.property  name="sATURN"
	 * @uml.associationEnd  
	 */
	SATURN,
	//Computer
	/**
	 * @uml.property  name="pC"
	 * @uml.associationEnd  
	 */
	PC, /**
	 * @uml.property  name="mAC"
	 * @uml.associationEnd  
	 */
	MAC, /**
	 * @uml.property  name="aNDROID"
	 * @uml.associationEnd  
	 */
	ANDROID,
	//Niintendo
	/**
	 * @uml.property  name="wII"
	 * @uml.associationEnd  
	 */
	WII, /**
	 * @uml.property  name="sUPERNINTENDO"
	 * @uml.associationEnd  
	 */
	SUPERNINTENDO, /**
	 * @uml.property  name="nINTENDO64"
	 * @uml.associationEnd  
	 */
	NINTENDO64, /**
	 * @uml.property  name="nGBA"
	 * @uml.associationEnd  
	 */
	NGBA, /**
	 * @uml.property  name="nGB"
	 * @uml.associationEnd  
	 */
	NGB, /**
	 * @uml.property  name="nDS"
	 * @uml.associationEnd  
	 */
	NDS, /**
	 * @uml.property  name="nDSI"
	 * @uml.associationEnd  
	 */
	NDSI, /**
	 * @uml.property  name="nDS3D"
	 * @uml.associationEnd  
	 */
	NDS3D,
	//Microsoft
	/**
	 * @uml.property  name="xBOX"
	 * @uml.associationEnd  
	 */
	XBOX, /**
	 * @uml.property  name="xBOX360"
	 * @uml.associationEnd  
	 */
	XBOX360,
	//Atari
	/**
	 * @uml.property  name="aTARI"
	 * @uml.associationEnd  
	 */
	ATARI,
	//Keima no PFP
	/**
	 * @uml.property  name="pFP"
	 * @uml.associationEnd  
	 */
	PFP, 
	//Kanade no PASOKON
	/**
	 * @uml.property  name="wINDING"
	 * @uml.associationEnd  
	 */
	WINDING,
	//Gundam no SYSTEM
	/**
	 * @uml.property  name="vEDA"
	 * @uml.associationEnd  
	 */
	VEDA;
}
