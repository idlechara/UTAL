using UnityEngine;
using System.Collections;

public class ScreenChange : MonoBehaviour {

	public GameObject MainMenu;
	public GameObject Scores;
	public GameObject LevelSelection;
	public GameObject Question1;

	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
	
	}

	public void MainMenuToScores(){
		MainMenu.SetActive (false);
		Scores.SetActive (true);
	}

	public void ScoresToMainMenu(){
		Scores.SetActive (false);
		MainMenu.SetActive (true);
	}

	public void MainMenuToLevelSelection(){
		MainMenu.SetActive (false);
		LevelSelection.SetActive (true);
	}

	public void LevelSelectionToMainMenu(){
		LevelSelection.SetActive (false);
		MainMenu.SetActive (true);
	}

	public void LevelSelectionToQuestion1(){
		LevelSelection.SetActive (false);
		Question1.SetActive (true);
	}

	//Devolver al main menu
	//Devolver a level selection
	public void Question1ToLevelSelection(){
		Question1.SetActive (false);
		LevelSelection.SetActive (true);
	}
}
