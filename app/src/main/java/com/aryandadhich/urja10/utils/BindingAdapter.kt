package com.aryandadhich.urja10.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aryandadhich.urja10.ui.coordinator.Coordinator
import com.aryandadhich.urja10.ui.coordinator.CoordinatorAdapter
import com.aryandadhich.urja10.ui.eventCoordinator.EventCoordinator
import com.aryandadhich.urja10.ui.eventCoordinator.EventCoordinatorAdapter
import com.aryandadhich.urja10.ui.forms.AvailableFormsAdapter
import com.aryandadhich.urja10.ui.forms.FillForm
import com.aryandadhich.urja10.ui.forms.Form
import com.aryandadhich.urja10.ui.forms.GetFilledFormAdapter
import com.aryandadhich.urja10.ui.games.common.players.Player
import com.aryandadhich.urja10.ui.games.common.players.PlayerListAdapter
import com.aryandadhich.urja10.ui.games.individualGames.carrom.CarromAdapter
import com.aryandadhich.urja10.ui.games.individualGames.carrom.CarromGame
import com.aryandadhich.urja10.ui.games.individualGames.chess.ChessAdapter
import com.aryandadhich.urja10.ui.games.individualGames.chess.ChessGame
import com.aryandadhich.urja10.ui.games.teamGames.badminton.BadmintonAdapter
import com.aryandadhich.urja10.ui.games.teamGames.badminton.BadmintonGame
import com.aryandadhich.urja10.ui.games.teamGames.basketball.BasketballAdapter
import com.aryandadhich.urja10.ui.games.teamGames.basketball.BasketballGame
import com.aryandadhich.urja10.ui.games.teamGames.cricket.CricketAdapter
import com.aryandadhich.urja10.ui.games.teamGames.cricket.CricketGame
import com.aryandadhich.urja10.ui.games.teamGames.football.FootballAdapter
import com.aryandadhich.urja10.ui.games.teamGames.football.FootballGame
import com.aryandadhich.urja10.ui.games.teamGames.snooker.SnookerAdapter
import com.aryandadhich.urja10.ui.games.teamGames.snooker.SnookerGame
import com.aryandadhich.urja10.ui.games.teamGames.squash.SquashAdapter
import com.aryandadhich.urja10.ui.games.teamGames.squash.SquashGame
import com.aryandadhich.urja10.ui.games.teamGames.tableTennis.TableTennisAdapter
import com.aryandadhich.urja10.ui.games.teamGames.tableTennis.TableTennisGame
import com.aryandadhich.urja10.ui.games.teamGames.teams.Team
import com.aryandadhich.urja10.ui.games.teamGames.teams.TeamAdapter
import com.aryandadhich.urja10.ui.games.teamGames.tennis.TennisAdapter
import com.aryandadhich.urja10.ui.games.teamGames.tennis.TennisGame
import com.aryandadhich.urja10.ui.games.teamGames.volleyball.VolleyballAdapter
import com.aryandadhich.urja10.ui.games.teamGames.volleyball.VolleyballGame
import com.aryandadhich.urja10.ui.houseCaptain.HouseCaptain
import com.aryandadhich.urja10.ui.houseCaptain.HouseCaptainAdapter

@BindingAdapter("houseCaptainListData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<HouseCaptain>?){
    val adapter = recyclerView.adapter as HouseCaptainAdapter
    adapter.submitList(data)
}


@BindingAdapter("coordinatorListData")
fun bindRecyclerViewOfCoordinator(recyclerView: RecyclerView, data: List<Coordinator>?){
    val adapter = recyclerView.adapter as CoordinatorAdapter
    adapter.submitList(data)
}


@BindingAdapter("eventCoordinatorListData")
fun bindRecyclerViewOfEventCoordinator(recyclerView: RecyclerView, data: List<EventCoordinator>?){
    val adapter = recyclerView.adapter as EventCoordinatorAdapter
    adapter.submitList(data)
}

@BindingAdapter("availableFormListData")
fun bindRecyclerViewOfAvailableForms(recyclerView: RecyclerView, data: List<Form>?){
    val adapter = recyclerView.adapter as AvailableFormsAdapter
    adapter.submitList(data)
}


@BindingAdapter("filledFormListData")
fun bindRecyclerViewOfFilledforms(recyclerView: RecyclerView, data: List<FillForm>?){
    val adapter = recyclerView.adapter as GetFilledFormAdapter
    adapter.submitList(data)
}

@BindingAdapter("gamePlayersListData")
fun bindRecyclerViewOfGamePlayers(recyclerView: RecyclerView, data: List<Player>?){
    val adapter = recyclerView.adapter as PlayerListAdapter
    adapter.submitList(data)
}

@BindingAdapter("teamsListData")
fun bindRecyclerViewOfTeams(recyclerView: RecyclerView, data: List<Team>?){
    val adapter = recyclerView.adapter as TeamAdapter
    adapter.submitList(data)
}


@BindingAdapter("basketballListData")
fun bindRecyclerViewOfBasketballGames(recyclerView: RecyclerView, data: List<BasketballGame>?){
    val adapter = recyclerView.adapter as BasketballAdapter
    adapter.submitList(data)
}

@BindingAdapter("footballListData")
fun bindRecyclerViewOfFootballGames(recyclerView: RecyclerView, data: List<FootballGame>?){
    val adapter = recyclerView.adapter as FootballAdapter
    adapter.submitList(data)
}


@BindingAdapter("badmintonListData")
fun bindRecyclerViewOfBadmintonGames(recyclerView: RecyclerView, data: List<BadmintonGame>?){
    val adapter = recyclerView.adapter as BadmintonAdapter
    adapter.submitList(data)
}

@BindingAdapter("tennisListData")
fun bindRecyclerViewOfTennisGames(recyclerView: RecyclerView, data: List<TennisGame>?){
    val adapter = recyclerView.adapter as TennisAdapter
    adapter.submitList(data)
}


@BindingAdapter("tableTennisListData")
fun bindRecyclerViewOfTableTennisGames(recyclerView: RecyclerView, data: List<TableTennisGame>?){
    val adapter = recyclerView.adapter as TableTennisAdapter
    adapter.submitList(data)
}

@BindingAdapter("squashListData")
fun bindRecyclerViewOfSquashGames(recyclerView: RecyclerView, data: List<SquashGame>?){
    val adapter = recyclerView.adapter as SquashAdapter
    adapter.submitList(data)
}
@BindingAdapter("snookerListData")
fun bindRecyclerViewOfSnookerGames(recyclerView: RecyclerView, data: List<SnookerGame>?){
    val adapter = recyclerView.adapter as SnookerAdapter
    adapter.submitList(data)
}

@BindingAdapter("volleyballListData")
fun bindRecyclerViewOfVolleyballGames(recyclerView: RecyclerView, data: List<VolleyballGame>?){
    val adapter = recyclerView.adapter as VolleyballAdapter
    adapter.submitList(data)
}
@BindingAdapter("cricketListData")
fun bindRecyclerViewOfCricketGames(recyclerView: RecyclerView, data: List<CricketGame>?){
    val adapter = recyclerView.adapter as CricketAdapter
    adapter.submitList(data)
}
@BindingAdapter("chessListData")
fun bindRecyclerViewOfChessGames(recyclerView: RecyclerView, data: List<ChessGame>?){
    val adapter = recyclerView.adapter as ChessAdapter
    adapter.submitList(data)
}

@BindingAdapter("carromListData")
fun bindRecyclerViewOfCarromGames(recyclerView: RecyclerView, data: List<CarromGame>?){
    val adapter = recyclerView.adapter as CarromAdapter
    adapter.submitList(data)
}